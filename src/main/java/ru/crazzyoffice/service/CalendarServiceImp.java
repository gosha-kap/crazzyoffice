package ru.crazzyoffice.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CalendarServiceImp implements CalendarService {
    @Override
    public Map<Integer, Boolean> generate(String d) {

        Map<Integer,Boolean> values = new TreeMap<>();
        LocalDate date = null;
        if(d==null)
            date = LocalDate.now();
        else
            date = LocalDate.parse(d);
        int year = date.getYear();
        int month = date.getMonthValue();

        //Cheching: if Year is leap
        boolean leapYear = false;
        if(year % 400 == 0) leapYear = true;
        else if (year % 100 == 0)   leapYear = false;
        else if(year % 4 == 0)  leapYear = true;
        else    leapYear = false;
        //Get days in month
        int count = date.getMonth().length(leapYear);

        LocalDate localDate = null;
        for(int i = 1; i<=count; i++) {
            localDate = LocalDate.of(year,month,i);
            values.put(i,localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                            localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) );
        }

        return values;
    }

    @Override
    public LocalDate getCurrentDate(Map<String, String> allParams) {

        LocalDate currentLocalDate = null;

        Integer dateYear = Integer.parseInt(allParams.getOrDefault("year","2000"));
        Integer dateMonth = Integer.parseInt(allParams.getOrDefault("month","1"));
        Integer dateDay = Integer.parseInt(allParams.getOrDefault("day","1"));

        if(allParams.containsKey("next"))
            currentLocalDate = LocalDate.of(dateYear,dateMonth,dateDay).plusMonths(1);
        else if(allParams.containsKey("previous"))
            currentLocalDate = LocalDate.of(dateYear,dateMonth,dateDay).minusMonths(1);
        else
            currentLocalDate = LocalDate.of(dateYear,dateMonth,dateDay);

        return currentLocalDate;


    }


}
