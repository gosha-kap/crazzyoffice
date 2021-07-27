package ru.crazzyoffice.bots.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.bots.commands.InputMessage;
import ru.crazzyoffice.bots.commands.ResponseMessage;
import ru.crazzyoffice.bots.commands.SendRequest;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.service.MainMenuService;

@Component
public class TelegramFacade {

    @Autowired
    private TelegramRepository repository;
    @Autowired
    private SendRequest sendRequest;
    @Autowired
    private MainMenuService mainMenuService;

    private static final Logger logger =
            LoggerFactory.getLogger(TelegramFacade.class);

    public BotApiMethod<?> handleUpdate(Update update) {
        /*
        Get Meta data from input message
         */
        Message message = update.getMessage();
        Long userId = message.getFrom().getId();
        Long  chatId = message.getChat().getId();
        String userName = message.getFrom().getFirstName();
        String lastName = message.getFrom().getLastName();
        /*
        Find User by Id or create new
         */
        TelegramUser telegramUser = repository.getByUserId(userId).orElse(
                new TelegramUser(userId, chatId, false,userName,lastName));
        /*
        Identify input type message else  return UNKNOWN
        Set default response message
        Check if user is autoriize
         */
        InputMessage inputMessage = InputMessage.getMessage(message.getText());
        ResponseMessage responseMessage = ResponseMessage.UNRECOGNIZE;
        Boolean isAutorize = telegramUser.getAutorised();
        /*
         Process commands for autorized users or not
         Return menu with result
         */
        if (isAutorize) {
            logger.debug("Send request by {}", "id=" + userId + " ,name=" + telegramUser.getFirst() + " " + telegramUser.getLast());
            switch (inputMessage) {
                case OPEN:
                    responseMessage = sendRequest.request();
                    break;
                case UNKNOWN:
                    break;
            }

        } else {
            logger.debug("Unauthorised request by , value {}", "id=" + userId + " ,name=" + telegramUser.getFirst() + " " + telegramUser.getLast() );
            switch (inputMessage) {
                case AUTORIZE:
                    repository.save(telegramUser);
                    responseMessage = ResponseMessage.REQUEST_SENDED;
                    break;
                case UNKNOWN:
                    responseMessage = ResponseMessage.ACCESS_FORBITTEN;
            }
        }
        return mainMenuService.getMenuMessage(chatId.toString(), responseMessage, isAutorize);
    }
}

