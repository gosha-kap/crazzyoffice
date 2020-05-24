package ru.crazzyoffice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/person" )
public class PersonController {

    @Autowired
    private PersonRepository repository;


    @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getOne(@PathVariable Integer id){
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
    public Person create(@RequestBody Person person){
        if (person.getId()!=null)
            throw new IllegalRequestDataException(person + " must be new (id=null)");
        return repository.save(person);
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Person update(@PathVariable  Integer id,
                         @RequestBody Person newPerson){

        if (newPerson.getId() != id)
            throw new IllegalRequestDataException(newPerson + " must be with id=" + id);
        return repository.findById(id)
                .map(person -> {
                    person.setAlias(newPerson.getAlias());
                    return repository.save(person);
                }).orElseThrow(() -> new NotFoundException("no entity find with id= " + id));

    }

}
