package com.skyrimAuction.telegramBot;

import com.skyrimAuction.dataBaseService.entities.User;
import com.skyrimAuction.dataBaseService.services.UserService;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.skyrimAuction.dataBaseService.entities.Code;
import com.skyrimAuction.dataBaseService.entities.Item;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

public class SkyrimAuctionTelegramBot extends TelegramLongPollingBot {

    private static int counter;

    @Autowired
    UserService service;

    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void switchCommand(Message message, String textMessage) throws InvalidObjectException {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<String> botCommands = new ArrayList<>();
        //Going to add another commands in future time
        botCommands.add("/checkAccount");
        List<KeyboardRow> commands = new ArrayList<>();
        for (String botCommand : botCommands) {
            KeyboardRow commandRow = new KeyboardRow();
            commandRow.add(botCommand);
            commands.add(commandRow);
        }
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(commands);
        replyKeyboardMarkup.setSelective(true);
        sendMessageRequest.setReplyMarkup(replyKeyboardMarkup);
        sendMessageRequest.setText(textMessage);
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
//        sendMessage(update.getMessage(), "replying for: " + update.getMessage().getText());
        if (update.hasMessage()) {
            List<User> userList = service.getUsers();
            for (User user : userList) {
                if (user.getTelegramID() == update.getMessage().getFrom().getId()) {
                    String message = "Personal account status:  " + user.getMoney();
                    switchCommand(update.getMessage(), message);
                    return;
                }
            }
        }
        } catch (InvalidObjectException | NullPointerException ex) {
            try {
                switchCommand(update.getMessage(), counter + " attempt to say that Igor pidor");
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
