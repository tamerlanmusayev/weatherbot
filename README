# WeatherBot for Baku

A simple Spring Boot WeatherBot that fetches weather data for Baku and sends updates via Telegram.

---

## Features

- Fetches current weather from OpenWeatherMap API
- Formats the weather info:
    - Weather OK / NOT OK
    - Temperature
    - Wind strength
- Sends updates to Telegram channel or chat
- Scheduled updates (cron configurable)

---

## Requirements

- Java 17+
- Maven
- Telegram Bot (created via @BotFather)
- OpenWeatherMap API Key
- Heroku account (for deployment)

---

## Configuration

Create `src/main/resources/application.properties`:

```properties
# OpenWeatherMap API
weather.api.key=YOUR_OPENWEATHERMAP_KEY
weather.api.url=https://api.openweathermap.org/data/2.5/weather

# Telegram Bot
telegram.bot.token=YOUR_BOT_TOKEN
telegram.chat.id=@your_channel_or_chat_id

# Scheduler
weather.cron=0 0 7 * * ?            # every day at 07:00
weather.cron.zone=Asia/Baku
server.port=8085                     # optional: Heroku uses $PORT
```
