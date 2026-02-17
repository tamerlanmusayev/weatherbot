package com.example.weatherbot.scheduler;

import com.example.weatherbot.service.WeatherService;
import com.example.weatherbot.service.WeatherFormatter;
import com.example.weatherbot.service.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherScheduler {

    private final WeatherService weatherService;
    private final WeatherFormatter weatherFormatter;
    private final TelegramService telegramService;

    @Value("${weather.cron}")
    private String cronExpression;

    @Value("${weather.cron.zone:UTC}")
    private String zone;

    public WeatherScheduler(WeatherService weatherService, WeatherFormatter weatherFormatter, TelegramService telegramService) {
        this.weatherService = weatherService;
        this.weatherFormatter = weatherFormatter;
        this.telegramService = telegramService;
    }

    @Scheduled(cron = "${weather.cron}", zone = "${weather.cron.zone}")
    public void sendWeatherUpdate() {
        String json = weatherService.getWeather();
        String message = weatherFormatter.formatWeather(json);
        telegramService.sendMessage(message);
    }
}
