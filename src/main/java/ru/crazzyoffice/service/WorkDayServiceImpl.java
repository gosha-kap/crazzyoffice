package ru.crazzyoffice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.crazzyoffice.entity.WorDayFactory;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.repository.WorkDayRepository;



import java.util.Comparator;
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
        workDays.forEach(x->x.datetoDayConverter());
        return  workDays.stream().sorted(Comparator.comparingInt(x->Integer.parseInt(x.getDate()))).collect(Collectors.toList());
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

        if (workDay == null)
            workDay = WorDayFactory.createEventForPerson(date, person,event);
        else
           workDay.updateRoleforPerson(event,person);
        saveWorkDay(workDay);
        return true;
    }

    @Override
    public boolean deleteWorkDayEvent(String date, int person) {

        WorkDay workDay = getWorkDaysForDay(date);
        if(workDay==null) return false;

        workDay.clearPersonRole(person);

        if(workDay.ifNoPersonSet())
           deleteWorkDay(workDay.getId());
        else
            saveWorkDay(workDay);
       return true;
    }


}
