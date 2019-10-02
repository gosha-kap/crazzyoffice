package ru.crazzyoffice.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "workDay")
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String date;

    private int dailyDriver;

    private int allDayWatcher;

    private int hollidayWatcher;

    public WorkDay() {

    }

    public WorkDay(String date, int dailyDriver, int allDayWatcher, int hollidayWatcher) {
        this.date = date;
        this.dailyDriver = dailyDriver;
        this.allDayWatcher = allDayWatcher;
        this.hollidayWatcher = hollidayWatcher;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDailyDriver() {
        return dailyDriver;
    }

    public void setDailyDriver(int dailyDriver) {
        this.dailyDriver = dailyDriver;
    }

    public int getAllDayWatcher() {
        return allDayWatcher;
    }

    public void setAllDayWatcher(int allDayWatcher) {
        this.allDayWatcher = allDayWatcher;
    }

    public int getHollidayWatcher() {
        return hollidayWatcher;
    }

    public void setHollidayWatcher(int hollidayWatcher) {
        this.hollidayWatcher = hollidayWatcher;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", dailyDriver=" + dailyDriver +
                ", allDayWatcher=" + allDayWatcher +
                ", hollidayWatcher=" + hollidayWatcher +
                '}';
    }

    public boolean ifNoPersonSet(){
        if(dailyDriver==0 && allDayWatcher==0 && hollidayWatcher==0)
            return true;
        return false;
    }

    public void clearPersonRole(Integer person){
        if(allDayWatcher==person)
            setAllDayWatcher(0);
        else if(dailyDriver==person)
            setDailyDriver(0);
        else if(hollidayWatcher==person)
            setHollidayWatcher(0);
    }


    public void updateRoleforPerson(String event, Integer person){

        clearPersonRole(person);
        if(event.equals("driver"))
            setDailyDriver(person);
        if(event.equals("pologaya"))
            setAllDayWatcher(person);
        if(event.equals("holidays")){
            setHollidayWatcher(person);
        }
    }


    public void datetoDayConverter(){
        Integer day = LocalDate.parse(getDate()).getDayOfMonth();
        setDate(day.toString());
    }


}
