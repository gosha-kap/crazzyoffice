package ru.crazzyoffice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public interface CalendarService {
    public Map<Integer,Boolean> generate(String date);

    public LocalDate getCurrentDate(Map <String,String> allParams);

}
