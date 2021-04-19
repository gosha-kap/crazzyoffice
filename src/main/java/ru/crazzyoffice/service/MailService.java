package ru.crazzyoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.crazzyoffice.bots.CrazyBot;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.repository.TelegramRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class MailService {

    @Autowired
    private CrazyBot crazyBot;

    @Autowired
    private TelegramRepository telegramRepository;


    @Bean
    @ServiceActivator(inputChannel = "imapChannel")
    public MessageHandler processNewEmail() {

        MessageHandler messageHandler = new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String mess = message.getPayload().toString();
                System.out.println("New email:" +mess);

                List<TelegramUser> chats = telegramRepository.getAll();
                if(chats.size()!=0){
                    String str = LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
                    for (TelegramUser user: chats) {
                        if(user.getAutorised()) {
                        try {
                            crazyBot.execute(new SendMessage(user.getChatId().toString(),mess+" "+str));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }}

                }

            }

        };

        return messageHandler;

    }
}


