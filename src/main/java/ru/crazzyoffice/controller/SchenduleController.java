package ru.crazzyoffice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.repository.EventJobRepo;
import ru.crazzyoffice.repository.JobRepository;
import ru.crazzyoffice.repository.PersonRepository;

import java.time.LocalDate;
import java.util.Objects;

@Controller
public class SchenduleController {

    private static final Logger logger =
            LoggerFactory.getLogger(SchenduleController.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EventJobRepo eventJobRepo;

    @GetMapping("/schendule")
    public ModelAndView loginPage(@RequestParam(name="date",required = false) String defaultDate){
        LocalDate localDate = LocalDate.now();
        if(Objects.nonNull(defaultDate)) localDate = LocalDate.parse(defaultDate);
        ModelAndView modelAndView = new ModelAndView("/schendule");
        modelAndView.addObject("persons",personRepository.findAll());
        modelAndView.addObject("jobs",jobRepository.findAll());
        modelAndView.addObject("defaultDate",localDate.toString());
        return modelAndView;
    }

    @PostMapping("/schendule")
    @Transactional
    public ModelAndView saveEvent(@RequestParam(name = "datepicker", required = true)  String date,
                                  @RequestParam(name = "person", required = true)  Integer personId,
                                  @RequestParam(name = "job", required = true)  Integer jobId) {
        JobEntity jobEntity = new JobEntity();
        try{
          jobEntity.setDate(LocalDate.parse(date));
          jobEntity.setJobType(jobRepository.getOne(jobId));
          jobEntity.setPerson(personRepository.getOne(personId));
          eventJobRepo.save(jobEntity);
         }
        catch (Exception e ){
            System.out.println(e.getMessage().toString());
        }


        return loginPage(date);
    }

    @Transactional
    @PostMapping("/schendule/delete")
    public ModelAndView deleteEvent(@RequestParam(name = "id",required = true) Integer eventId  ){

        logger.debug("deleteEvent() is executed, value {}", "mkyong");

        eventJobRepo.deleteById(eventId);
        return loginPage(null);
    }

}
