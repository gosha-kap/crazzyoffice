package ru.crazzyoffice.service;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.repository.WorkDayRepository;

import java.net.URL;
import java.time.LocalDate;

@Service
public class WorkDayService {

    @Autowired
    private WorkDayRepository repository;

    public WorkDay getTodayWorkday() throws Exception{
        return null;
    }

}
