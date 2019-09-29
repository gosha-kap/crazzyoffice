package ru.crazzyoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional
    public List<Person> getPerson() {

        return personRepository.findAll();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {

    }

    @Override
    @Transactional
    public Person getPerson(int theId) {
        return null;
    }

    @Override
    @Transactional
    public void deletePerson(int theId) {

    }
}
