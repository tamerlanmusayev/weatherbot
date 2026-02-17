package com.example.weatherbot.service;

import com.example.weatherbot.config.AppConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final AppConfig appConfig;
    private final OkHttpClient client = new OkHttpClient();

    public WeatherService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public String getWeather() {
        String url = appConfig.getWeatherApiUrl()
                + "?q=Baku"
                + "&appid=" + appConfig.getWeatherApiKey()
                + "&units=metric";

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() == null) {
                    return "No weather data";
                }
                return response.body().string();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather", e);
        }
    }
}
