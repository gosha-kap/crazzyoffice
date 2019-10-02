package ru.crazzyoffice.entity;

public class WorDayFactory {
    public static WorkDay createEventForPerson(String date, Integer person, String event) {
        if (event.equals("driver"))
            return new WorkDay(date, person, 0, 0);
        if (event.equals("pologaya"))
            return new WorkDay(date, 0, person, 0);
        if (event.equals("holidays"))
            return new WorkDay(date, 0, 0, person);

        return new WorkDay();

    }
}
