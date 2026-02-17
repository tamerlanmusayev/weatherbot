package com.example.weatherbot.service;

import com.example.weatherbot.config.AppConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramService {

    private final AppConfig appConfig;
    private TelegramLongPollingBot bot;

    public TelegramService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @PostConstruct
    public void init() {
        try {
            bot = new TelegramLongPollingBot() {
                @Override
                public String getBotUsername() {
                    return "WeatherBot";
                }

                @Override
                public String getBotToken() {
                    return appConfig.getTelegramBotToken();
                }

                @Override
                public void onUpdateReceived(
                        org.telegram.telegrambots.meta.api.objects.Update update
                ) {
                    // not used
                }
            };

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            throw new IllegalStateException("Failed to initialize Telegram bot", e);
        }
    }

    public void sendMessage(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(appConfig.getTelegramChatId());
        message.setText(text);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
