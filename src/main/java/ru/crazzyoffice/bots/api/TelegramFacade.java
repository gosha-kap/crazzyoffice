package ru.crazzyoffice.bots.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.crazzyoffice.ConvertToMessage;
import ru.crazzyoffice.entity.JobEntity;
import ru.crazzyoffice.entity.TelegramUser;
import ru.crazzyoffice.entity.WorkDay;
import ru.crazzyoffice.error.NotFoundException;
import ru.crazzyoffice.repository.EventJobRepo;
import ru.crazzyoffice.repository.TelegramRepository;
import ru.crazzyoffice.repository.WorkDayRepository;
import ru.crazzyoffice.service.MainMenuService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
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
    private WorkDayRepository workDayRepository;

    @Autowired
    private EventJobRepo eventJobRepo;



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
                        try {
                            arduinoSendRequest.doGet(POSITION.Pologaya);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        outMsg = "Пожалуйста, проезжайте";
                        break;
                    case "Ворота Гараж":
                        try {
                            arduinoSendRequest.doGet(POSITION.Garage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        outMsg = "Пожалуйста, проезжайте";
                        break;

                    case "Рассписание на неделю":
                        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
                        LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

                        /*List<WorkDay> weekWorkDays = workDayRepository.getWeekDays(monday,sunday);
                        outMsg = ConvertToMessage.convertWorkDays(weekWorkDays);*/

                        List<JobEntity> weekJobs = eventJobRepo.getWeekEvents(monday,sunday);
                        outMsg = ConvertToMessage.convertEvents(weekJobs);

                        break;
                    default:
                        outMsg = "Воспользуйтесь главным меню";
                }
                return mainMenuService.getMainMenuMessage(chatId, outMsg);
            }
         else
             return new SendMessage(chatId,"Вы не авторизованы");
         }
        else
            return null;



    }
}
