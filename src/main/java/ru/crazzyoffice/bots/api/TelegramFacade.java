package ru.crazzyoffice.bots.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.ConvertToMessage;
import ru.crazzyoffice.controller.SchenduleController;
import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.repository.EventJobRepo;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.service.MainMenuService;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


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


    @Autowired
    private EventJobRepo eventJobRepo;

    private static final Logger logger =
            LoggerFactory.getLogger(SchenduleController.class);

    public BotApiMethod<?> handleUpdate(Update update) {

        Message message = update.getMessage();
        String inputMsg = message.getText();
        String outMsg;
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();

        if (message != null && message.hasText()) {
            TelegramUser telegramUser = repository.getByUserId(userId).orElse(null);
            if(telegramUser!=null) {
                switch (inputMsg){
                    case "Шлагбаум Пологая":
                        logger.debug("Trying to open POLOGAYA by , value {}", telegramUser);
                        try {
                            arduinoSendRequest.doGet(POSITION.Pologaya);
                        } catch (IOException e) {
                            logger.error("Error  POLOGAYA : , value {}", e.getMessage());
                            outMsg = "Произошла ошибка";
                            break;
                        } catch (InterruptedException e) {
                            logger.error("Error  POLOGAYA : , value {}", e.getMessage());
                            outMsg = "Ошибка.Нет связи.";
                            break;
                        }
                        outMsg = "Пожалуйста, проезжайте";
                        break;
                    case "Ворота Гараж":
                        logger.debug("Trying to open GARAGE by , value {}", telegramUser);
                        try {
                            arduinoSendRequest.doGet(POSITION.Garage);
                        } catch (IOException e) {
                            logger.error("Error  Garage : , value {}", e.getMessage());
                            outMsg = "Произошла ошибка";
                            break;
                        } catch (InterruptedException e) {
                            logger.error("Error  Garage : , value {}", e.getMessage());
                            outMsg = "Ошибка.Нет связи.";
                            break;
                        }
                        outMsg = "Пожалуйста, проезжайте";
                        break;

                    case "Рассписание на неделю":
                        logger.debug("Get shendule by  , value {}", telegramUser);
                        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
                        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

                        List<JobEntity> weekJobs = eventJobRepo.getWeekEvents(monday,sunday);
                        outMsg = ConvertToMessage.convertEvents(weekJobs);

                        break;
                    default:
                        outMsg = "Воспользуйтесь главным меню";
                }
                return mainMenuService.getMainMenuMessage(chatId, outMsg);
            }
         else
             logger.debug("Unauthorised request by , value {}", telegramUser);
             return new SendMessage(chatId,"Вы не авторизованы");
         }
        else
            return null;



    }
}
