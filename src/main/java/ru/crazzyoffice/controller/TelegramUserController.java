package ru.crazzyoffice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.TelegramRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pologaya")
public class TelegramUserController {

    @Autowired
    private TelegramRepository repository;

    private static final Logger logger =
            LoggerFactory.getLogger(TelegramUserController.class);


    @GetMapping
    public ModelAndView loginPage(){

            ModelAndView modelAndView = new ModelAndView("/pologaya");
            modelAndView.addObject("tmusers",repository.getAll());
            return modelAndView;

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public String getOne(@PathVariable Integer id){
        TelegramUser telegramUser = Optional.of(repository.findById(id)).
                get().orElseThrow(()-> new NotFoundException("no user with id = "+id+" found"));
       // ------------------------------------------------



       //-------------------------------------------------
        return telegramUser.toString();
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
