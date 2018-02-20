package com.skyrimAuction.telegramBot;

import com.skyrimAuction.dataBaseService.entities.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.skyrimAuction.dataBaseService.entities.Code;
import com.skyrimAuction.dataBaseService.entities.Item;
import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;

public class SkyrimAuctionTelegramBot extends TelegramLongPollingBot {

    @Autowired
    EbeanServer server;

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

    }

    @Override
    public void onUpdateReceived(Update update) {
        sendMessage(update.getMessage(), "replying for: " + update.getMessage().getText());
        List<User> userList = server.find(User.class).findList();

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
