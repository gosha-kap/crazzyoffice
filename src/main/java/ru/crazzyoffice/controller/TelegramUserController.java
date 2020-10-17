package ru.crazzyoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.TelegramRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tmuser")
public class TelegramUserController {

    @Autowired
    private TelegramRepository repository;

    @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<TelegramUser> getAll(){
         return  repository.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelegramUser getOne(@PathVariable Integer id){
        return Optional.of(repository.findById(id)).
                get().orElseThrow(()-> new NotFoundException("no user with id = "+id+" found"));
    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if (repository.delete(id)==0) throw  new NotFoundException("no user with id = "+id+" found");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TelegramUser create(@RequestBody TelegramUser telegramUser){
        if (telegramUser.getId()!=null)
            throw new IllegalRequestDataException(telegramUser + " must be new (id=null)");
        return repository.save(telegramUser);
    }


}
