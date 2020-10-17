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
import ru.crazzyoffice.entity.DEPARTMENT;
import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.Person;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.repository.EventJobRepo;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.service.MainMenuService;

import javax.validation.constraints.Null;
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
            LoggerFactory.getLogger(TelegramFacade.class);

    public BotApiMethod<?> handleUpdate(Update update) {

        Message message = update.getMessage();
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        String userName = message.getContact().getFirstName();
        String lastName = message.getContact().getLastName();
        String phone = message.getContact().getPhoneNumber();
        String outMsg;

        TelegramUser telegramUser = repository.getByUserId(userId).orElse(
                new TelegramUser(userId, new Person(userName, lastName, phone), false));

        if (telegramUser.getAutorised()) {
            DEPARTMENT department = telegramUser.getPerson().getDepartment();
            String personName = telegramUser.getPerson().getFirstName()+"_"+telegramUser.getPerson().getLastName();
            switch (inputMsg) {
                case "Шлагбаум Пологая":
                    logger.debug("Open POLOGAYA by {}",personName );
                    try {
                        arduinoSendRequest.doGet(POSITION.Pologaya);
                    } catch (IOException e) {
                        logger.error("Error  POLOGAYA :  {}", e.getMessage());
                        outMsg = "Ошибка: Нет ответа.";
                        break;
                    } catch (InterruptedException e) {
                        logger.error("Error  POLOGAYA :  {}", e.getMessage());
                        outMsg = "Произошла ошибка";
                        break;
                    }
                    outMsg = "Пожалуйста, проезжайте";
                    break;
                case "Ворота Гараж":
                    if (department.equals(DEPARTMENT.VSK)) {
                        logger.debug("Trying to open GARAGE by {}", personName);
                        try {
                            arduinoSendRequest.doGet(POSITION.Garage);
                        } catch (IOException e) {
                            logger.error("Error  Garage :  {}", e.getMessage());
                            outMsg = "Ошибка: Нет ответа.";
                            break;
                        } catch (InterruptedException e) {
                            logger.error("Error  Garage : {}", e.getMessage());
                            outMsg = "Произошла ошибка";
                            break;
                        }
                        outMsg = "Пожалуйста, проезжайте";
                    } else outMsg = "Отказано в доступе";
                    break;

                    /*case "Рассписание на неделю":
                        logger.debug("Get shendule by  , value {}", telegramUser);
                        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
                        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

                        List<JobEntity> weekJobs = eventJobRepo.getWeekEvents(monday,sunday);
                        outMsg = ConvertToMessage.convertEvents(weekJobs);
                        break;*/
                default:
                    outMsg = "Воспользуйтесь главным меню";
            }
            return mainMenuService.getMainMenuMessage(chatId, outMsg, department);
        } else {
            logger.debug("Unauthorised request by , value {}", "id=" + userId + " ,name=" + userName + " " + lastName + " ,phone=" + phone);
            switch (inputMsg) {
                case "Авторизоваться":
                    repository.save(telegramUser);
                    outMsg = "Запрос отправлен";
                    break;
                default:
                    outMsg = "Вы не авторизованы";
            }
            return mainMenuService.getAuthMenuMessage(chatId, outMsg);
        }

    }
}

