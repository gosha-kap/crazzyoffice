package ru.crazzyoffice.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.crazzyoffice.ConvertToMessage;
import ru.crazzyoffice.bots.CrazyBot;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.repository.WorkDayRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Component
public class TelegramJob  {

    @Autowired
    private CrazyBot crazyBot;

    @Autowired
    private WorkDayRepository repository;

    @Autowired
    private TelegramRepository telegramRepository;

    //@Scheduled(cron = "0 11,20 * * * * ")
    @Scheduled(cron = "0 0 21 ? * *")
    public void todayScheduled()  {

        System.out.println("Task Running");
        List<TelegramUser> chats = telegramRepository.getAll();
        if(chats.size()==0)
            return;
        LocalDate localDate;
        if(LocalTime.now().isBefore(LocalTime.NOON))
            localDate = LocalDate.now();
        else
            localDate = LocalDate.now().plusDays(1);

        WorkDay todayWork = repository.findWorkDayByDate(localDate).orElse(new WorkDay(localDate));
        String todayWorksPrepered = ConvertToMessage.convertWorkDay(todayWork);


         for (TelegramUser user: chats) {
            try {
                crazyBot.execute(new SendMessage(user.getChatId(),todayWorksPrepered));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

   }
