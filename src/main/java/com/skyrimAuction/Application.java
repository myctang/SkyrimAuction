package com.skyrimAuction;

import com.skyrimAuction.telegramBot.SkyrimAuctionTelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotOptions;
import org.telegram.telegrambots.generics.BotSession;
import org.telegram.telegrambots.generics.LongPollingBot;

@SpringBootApplication
public class Application {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) throws TelegramApiRequestException {
        SpringApplication.run(Application.class, args);
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        BotSession skyrimAuctionSession = null;

            skyrimAuctionSession = botsApi.registerBot(new SkyrimAuctionTelegramBot());

       while (true) {
            if (!skyrimAuctionSession.isRunning()) {
                skyrimAuctionSession.stop();
                skyrimAuctionSession.start();
            }
       }
    }
}
