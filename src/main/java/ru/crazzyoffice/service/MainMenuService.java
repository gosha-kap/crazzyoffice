package ru.crazzyoffice.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.crazzyoffice.bots.commands.InputMessage;
import ru.crazzyoffice.bots.commands.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/*
Display  menu,  depends on  user privileges
 */
@Service
public class MainMenuService {


    /*
    I have only one button for each menu ,
     so use one metod  "getMenuKeyboard" to create  menus whats differents only keyboard values
     */
    public SendMessage getMenuMessage(String chatId, ResponseMessage message, boolean isAutorize) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = getMenuKeyboard(isAutorize);
        return createMessageWithKeyboard(chatId, message, replyKeyboardMarkup);

    }

    private ReplyKeyboardMarkup getMenuKeyboard(boolean isAutorize) {

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        if (isAutorize) {
            row.add(new KeyboardButton(InputMessage.OPEN.getCommand()));
        } else {
            row.add(new KeyboardButton(InputMessage.AUTORIZE.getCommand()));
        }

        keyboard.add(row);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(String chatId,
                                                  ResponseMessage message,
                                                  final ReplyKeyboardMarkup replyKeyboardMarkup) {
        final SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message.getCommand());
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        return sendMessage;
    }




}
