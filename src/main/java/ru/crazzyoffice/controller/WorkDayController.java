package ru.crazzyoffice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.entity.model.Event;
import ru.crazzyoffice.error.IllegalRequestDataException;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.WorkDayRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/calendar", produces =  MediaType.APPLICATION_JSON_VALUE)
public class WorkDayController {


    @Autowired
    private WorkDayRepository workDayRepository;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WorkDay> workdaysForMonth() {
        LocalDate  currentLocalDate = LocalDate.now();
        List<WorkDay> workDays = workDayRepository.findMonthEntries(currentLocalDate.getMonthValue(), currentLocalDate.getYear());
        return workDays.stream().sorted(Comparator.comparing(WorkDay::getDate)).collect(Collectors.toList());

    }
    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WorkDay getOneWorkday(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return Optional.of(workDayRepository.findWorkDayByDate(date)).
                get().orElseThrow(()-> new NotFoundException("no job entry with id= "+date+" found"));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public WorkDay createWorkDay(@RequestBody WorkDay workDay){
        if (workDay.getId()!=null)
            throw new IllegalRequestDataException(workDay + " must be new (id=null)");
        return workDayRepository.save(workDay);
    }

    @DeleteMapping("{workDayID}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteWorkDay(@PathVariable int workDayID){
        if (workDayRepository.delete(workDayID)==0)
            throw  new NotFoundException("no job entry with id= "+workDayID+" found");
    }


    @PutMapping(value = "{workDayId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public WorkDay updateWorkDay(@PathVariable  int  workDayId,
                                 @RequestBody WorkDay workDay) {
        if (workDay.getId() != workDayId)
            throw new RuntimeException(workDay + " must be with id=" + workDayId);
        return workDayRepository.findById(workDayId)
                .map(workday -> {
                    workday.setDate(workDay.getDate());
                    workday.setWorkers(workDay.getWorkers());
                    return workDayRepository.save(workday);
                }).orElseThrow(() -> new RuntimeException("no entity find with id = " + workDayId));
    }



}
