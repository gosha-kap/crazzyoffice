package ru.crazzyoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.bots.CrazyBot;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/")
public class TelegramController {

    @Autowired
    private CrazyBot crazyBot;

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
            return crazyBot.onWebhookUpdateReceived(update);
    }
    @GetMapping
    public void redirect(HttpServletResponse response) throws Exception
    {
        response.sendRedirect("pologaya");

    }
}
