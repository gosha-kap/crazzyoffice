package ru.crazzyoffice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.repository.WorkDayRepository;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkDayServiceImpl implements WorkDayService {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WorkDay> getWorkDaysForMonth(String date) {

        List<WorkDay> workDays = workDayRepository.findMonthEntries(date);
        workDays.stream().forEach(x->x.setDate(x.getDate().substring(x.getDate().length()-2)));
        workDays.stream().forEach(x->{if(x.getDate().startsWith("0")) x.setDate(x.getDate().substring(1));});
        workDays = workDays.stream().sorted((x1,x2)-> {
            Integer value_x1 = Integer.parseInt(x1.getDate());
            Integer value_x2 = Integer.parseInt(x2.getDate());
            return value_x1.compareTo(value_x2);}
       ).collect(Collectors.toList());
        workDays.stream().forEach(System.out::println);
       return workDays;
    }

    @Override
    @Transactional(readOnly = true)
    public WorkDay getWorkDaysForDay(String date) {
        return workDayRepository.findWorkDayByDate(date);
    }

    @Override
    @Transactional
    public void saveWorkDay(WorkDay day) {
            workDayRepository.save(day);
    }


    @Override
    @Transactional
    public WorkDay getWorkDay(int theId) {

        WorkDay workDay =  workDayRepository.findById(theId).orElse(new WorkDay());
        return  workDay;
    }

    @Override
    @Transactional
    public void deleteWorkDay(int theId) {
        workDayRepository.deleteById(theId);
    }

    @Override
    public boolean createWorkDayEvent(String date, String event, int person) {
        WorkDay workDay = getWorkDaysForDay(date);

        if (workDay == null) {
            //create event
            if (event.equals("driver")) {
                workDay = new WorkDay(date, person, 0, 0);
            } else if (event.equals("pologaya")) {
                workDay = new WorkDay(date, 0, person, 0);
            } else if (event.equals("holidays")) {
                workDay = new WorkDay(date, 0, 0, person);
            }
       }
        else {
            //update event
            if(event.equals("driver"))
            {
                workDay.setDailyDriver(person);
                if(workDay.getAllDayWatcher() == person) workDay.setAllDayWatcher(0);
                if(workDay.getHollidayWatcher()==person) workDay.setHollidayWatcher(0);
            }
            else if(event.equals("pologaya")){
                workDay.setAllDayWatcher(person);
                if(workDay.getHollidayWatcher()==person) workDay.setHollidayWatcher(0);
                if(workDay.getDailyDriver()==person) workDay.setDailyDriver(0);
            }
            else if(event.equals("holidays")){
                workDay.setHollidayWatcher(person);
                if(workDay.getAllDayWatcher()==person) workDay.setAllDayWatcher(0);
                if(workDay.getDailyDriver()==person) workDay.setDailyDriver(0);
            }



            /////////////////////////////////////
        }
        saveWorkDay(workDay);
        return true;
    }

    @Override
    public boolean deleteWorkDayEvent(String date, int person) {
        WorkDay workDay = getWorkDaysForDay(date);
        if(workDay==null) return false;
        if(workDay.getAllDayWatcher()==person) workDay.setAllDayWatcher(0);
       else if(workDay.getDailyDriver()==person) workDay.setDailyDriver(0);
       else if(workDay.getHollidayWatcher()==person) workDay.setHollidayWatcher(0);
       if(workDay.getAllDayWatcher()==0 && workDay.getHollidayWatcher()==0 && workDay.getDailyDriver()==0)
           deleteWorkDay(workDay.getId());
       else saveWorkDay(workDay);
       return true;
    }


}
