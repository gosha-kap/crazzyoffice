package ru.crazzyoffice.controller;

import ru.crazzyoffice.entity.JobType;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.WorkDay;

import java.time.LocalDate;
import java.util.HashMap;

public  class TestData {


    public static JobType  jobType1TestData = new JobType(1,"Водитель");
    public static JobType  jobType2TestData = new JobType(2,"Дежурный");
    public static JobType  jobType3TestData = new JobType(3,"На телефоне");

    public static Person person1TestData = new Person(1,"Иванов");
    public static Person person2TestData = new Person(2,"Петров");
    public static Person person3TestData = new Person(3,"Сидоров");

  /*  public static WorkDay workDayTestData = new WorkDay(LocalDate.now().withDayOfMonth(1));
    public static WorkDay workDay2TestData = new WorkDay(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));*/

    public static WorkDay workDayTestData = new WorkDay(LocalDate.now().withDayOfMonth(10));
    public static WorkDay workDay2TestData = new WorkDay(LocalDate.now().withDayOfMonth(1));
}
