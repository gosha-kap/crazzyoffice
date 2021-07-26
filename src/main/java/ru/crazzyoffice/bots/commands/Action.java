package ru.crazzyoffice.bots.commands;

import ru.crazzyoffice.bots.api.TelegramFacade;

public abstract class Command {
    TelegramFacade telegramFacade;

    public Command(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    public abstract String requestOpen();
    public abstract String requestAutorize();

}
