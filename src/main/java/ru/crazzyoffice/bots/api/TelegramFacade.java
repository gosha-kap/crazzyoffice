package ru.crazzyoffice.bots.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.service.MainMenuService;

import java.io.IOException;


/**
 * @author Sergei Viacheslaev
 */
@Component
public class TelegramFacade {

    @Autowired
    private TelegramRepository repository;

    @Autowired
    private ArduinoSendRequest arduinoSendRequest;

    @Autowired
    private MainMenuService mainMenuService;




    private static final Logger logger =
            LoggerFactory.getLogger(TelegramFacade.class);

    public BotApiMethod<?> handleUpdate(Update update) {


        Message message = update.getMessage();

        String inputMsg = message.getText();

        Long userId = message.getFrom().getId();

        Long  chatId = message.getChat().getId();

        String userName = message.getFrom().getFirstName();

        String lastName = message.getFrom().getLastName();



        String outMsg;

        TelegramUser telegramUser = repository.getByUserId(userId).orElse(
                new TelegramUser(userId, chatId, false,userName,lastName));

        if (telegramUser.getAutorised()) {

            String personName = telegramUser.getFirst()+"_"+telegramUser.getLast();
            switch (inputMsg) {
                case "Шлагбаум Пологая":
                    logger.debug("Open POLOGAYA by {}",personName );
                    try {
                        arduinoSendRequest.doGet(POSITION.Pologaya);
                    } catch (IOException e) {
                        logger.error("Error  POLOGAYA :  {}", e.getMessage());
                        outMsg = "Ошибка: IOException";
                        break;
                    } catch (InterruptedException e) {
                        logger.error("Error  POLOGAYA :  {}", e.getMessage());
                        outMsg = "InterruptedException";
                        break;
                    }
                    outMsg = "Пожалуйста, проезжайте";
                    break;

                default:
                    outMsg = "Unknown command";
            }
            return mainMenuService.getMainMenuMessage(chatId.toString(), outMsg);
        } else {
            logger.debug("Unauthorised request by , value {}", "id=" + userId + " ,name=" + telegramUser.getFirst() + " " + telegramUser.getLast() );
            switch (inputMsg) {
                case "Авторизоваться":
                    repository.save(telegramUser);
                    outMsg = "Запрос отправлен";
                    break;
                default:
                    outMsg = "Вы не авторизованы";
            }
            return mainMenuService.getAuthMenuMessage(chatId.toString(), outMsg);
        }

    }
}

