package ru.crazzyoffice.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.crazzyoffice.entity.DEPARTMENT;

import java.util.ArrayList;
import java.util.List;

/**
 * Управляет отображением главного меню в чате.
 *
 * @author Sergei Viacheslaev
 */
@Service
public class MainMenuService {

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage , DEPARTMENT department) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard(department);
        final SendMessage mainMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);

        return mainMenuMessage;
    }

    private ReplyKeyboardMarkup getMainMenuKeyboard(DEPARTMENT department) {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Шлагбаум Пологая"));
        keyboard.add(row1);

        if(department.equals(DEPARTMENT.VSK)){
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Ворота Гараж"));
        keyboard.add(row3);}

        //  KeyboardRow row2 = new KeyboardRow();
        // row2.add(new KeyboardButton("Рассписание на неделю"));
       // keyboard.add(row2);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }

    public BotApiMethod<?> getAuthMenuMessage(final long chatId, final String textMessage) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getAuthMenuKeyboard();
        final SendMessage authMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, replyKeyboardMarkup);

        return authMenuMessage;
    }

    private ReplyKeyboardMarkup getAuthMenuKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

         KeyboardRow row = new KeyboardRow();
         row.add(new KeyboardButton("Авторизоваться"));
         keyboard.add(row);
         replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
