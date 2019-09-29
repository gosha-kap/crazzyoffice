package ru.crazzyoffice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.service.CalendarService;
import ru.crazzyoffice.service.PersonService;
import ru.crazzyoffice.service.WorkDayService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workDay")
public class WorkDayController {

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CalendarService calendarService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");


    @GetMapping("/list")
    public String listPersons(Model theModel) {
        fillModel(theModel, LocalDate.now());
        return "list";
    }

    @PostMapping("/list-search")
    public String history(@RequestParam(value = "step",required = false) String step,
                          @RequestParam(value = "edit",required = false) String edit,
                          @RequestParam(value = "year",required = false) Integer dateYear,
                          @RequestParam(value = "month",required = false) Integer dateMonth,
                          @RequestParam(value = "day",required = false) Integer dateDay,
                          @RequestParam(value = "event",required = false) String event,
                          @RequestParam(value = "person",required = false) Integer person,Model theModel){


        ////Genegate currentLocalDate for proccess
        LocalDate currentLocalDate = null;

            //For navigation
            if(step!=null){
                if(step.equals("next")) currentLocalDate = LocalDate.of(dateYear,dateMonth,1).plusMonths(1);
                else if(step.equals("previous")) currentLocalDate = LocalDate.of(dateYear,dateMonth,1).minusMonths(1);
            }
            //For edit or delete event for certain day
            else if(dateDay!=null){
                currentLocalDate = LocalDate.of(dateYear,dateMonth,dateDay);
            }
            //when we need days from calendarservice
            else {
                currentLocalDate = LocalDate.of(dateYear,dateMonth,1);
            }




        ///CRUD Workday event in edit Mode
        if(edit!=null && person != null && dateDay != null) {
            String date = currentLocalDate.toString();
            if (edit.equals("edit")) {
                workDayService.createWorkDayEvent(date, event, person);
            }
            if (edit.equals("del") ) {
                workDayService.deleteWorkDayEvent(date, person);
            }
        }

        ///Fill model with date
        fillModel(theModel, currentLocalDate);


        if(edit!=null){
             if(edit.equals("edit") || edit.equals("del")) return "edit";
        }
        return "list";
    }

    private void fillModel(Model theModel, LocalDate currentLocalDate) {
        String date = currentLocalDate.toString();
        List<Person> persons = personService.getPerson();
        Map<Integer,Boolean> days = calendarService.generate(date);
        List<WorkDay> workDays = workDayService.getWorkDaysForMonth(date);

        theModel.addAttribute("persons", persons);
        theModel.addAttribute("days",days);
        theModel.addAttribute("workdays",workDays);
        theModel.addAttribute("date",currentLocalDate.format(formatter));
        theModel.addAttribute("month",currentLocalDate.getMonthValue());
        theModel.addAttribute("year",currentLocalDate.getYear());
    }


}
