package ru.crazzyoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.crazzyoffice.entity.model.Event;
import ru.crazzyoffice.entity.util.toDTO;
import ru.crazzyoffice.repository.EventJobRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobevent")
public class EventJobController {

    @Autowired
    private EventJobRepo repo;

    @GetMapping
    public List<Event> getForMonth(@RequestParam(name="start",required = false) String startDate,
                                    @RequestParam(name="end",required = false) String endDate){
        LocalDate fisrt = ZonedDateTime.parse(startDate).toLocalDate();
        LocalDate last = ZonedDateTime.parse(endDate).toLocalDate();
           return  repo.findAllByDateIsGreaterThanEqualAndDateIsLessThanEqual(fisrt,last)
                .stream().map(x-> toDTO.toEvent(x)).collect(Collectors.toList());


    }
}
