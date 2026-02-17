package com.example.weatherbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class WeatherFormatter {

    private final ObjectMapper mapper = new ObjectMapper();

    public String formatWeather(String json) {
        try {
            JsonNode root = mapper.readTree(json);

            double temp = root.path("main").path("temp").asDouble();
            double windSpeed = root.path("wind").path("speed").asDouble();
            String mainWeather = root.path("weather").get(0).path("main").asText();

            String weatherOk = (temp >= 15 && temp <= 30 && mainWeather.equalsIgnoreCase("Clear"))
                    ? "Weather is OK" : "Weather is NOT OK";

            String wind = windSpeed > 10 ? "🌬 BIG WIND" : "no wind";

            return String.format("%s\n%s\n🌡 Temperature: %.1f°C", weatherOk, wind, temp);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing weather";
        }
    }
}
