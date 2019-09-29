package ru.crazzyoffice.service;

import ru.crazzyoffice.entity.WorkDay;

import java.util.List;
import java.util.Optional;

public interface WorkDayService {


    public List<WorkDay> getWorkDaysForMonth(String date);

    public WorkDay getWorkDaysForDay(String date);

    public void saveWorkDay(WorkDay day);


    public WorkDay getWorkDay(int theId);

    public void deleteWorkDay(int theId);

    public boolean createWorkDayEvent(String date,String event, int person);

    public boolean deleteWorkDayEvent(String date,int person);
}
