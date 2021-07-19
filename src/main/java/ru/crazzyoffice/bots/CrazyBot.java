package ru.crazzyoffice.bots;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.bots.api.TelegramFacade;

public class CrazyBot extends TelegramWebhookBot {
    private  String botUserName;
    private  String botToken;
    private  String botPath;

    @Autowired
    private TelegramFacade telegramFacade;

    public CrazyBot(DefaultBotOptions options) {
        super(options);
    }

    public CrazyBot(){
        super();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
                 return telegramFacade.handleUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
      }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }
}
