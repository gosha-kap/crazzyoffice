package ru.crazzyoffice.service;

import ru.crazzyoffice.entity.Person;

import java.util.List;

public interface PersonService {


    public List<Person> getPerson();

    public void savePerson(Person person);

    public Person getPerson(int theId);

    public void deletePerson(int theId);
}
