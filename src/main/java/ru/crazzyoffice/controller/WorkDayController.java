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
    public String navigationMOnths(@RequestParam Map<String,String> allParams, Model theModel){

        LocalDate currentDate = calendarService.getCurrentDate(allParams);
        fillModel(theModel, currentDate);

        if(allParams.containsKey("edit"))
            return "edit";
        else
            return "list";

    }

    @PostMapping("/list-edit")
    public String edit(@RequestParam Map<String,String> allParams, Model theModel){

        Integer person = Integer.parseInt(allParams.getOrDefault("person","0"));
        LocalDate currentLocalDate = calendarService.getCurrentDate(allParams);

        if(allParams.containsKey("save"))
            workDayService.createWorkDayEvent(currentLocalDate.toString(), allParams.get("event"), person);
        if(allParams.containsKey("del"))
            workDayService.deleteWorkDayEvent(currentLocalDate.toString(),person);

        fillModel(theModel, currentLocalDate);

        if(allParams.containsValue("exit"))
            return "list";
        else
            return "edit";

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
