package ru.beck.telegram_bot_second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramBotSecondApplication {
    /**
     * Инициализируем контекст для telegram API.
     * Библиотечный класс, внутри -
     * ApiContext.register(BotSession.class, DefaultBotSession.class);
     * ApiContext.register(Webhook.class, DefaultWebhook.class);
     * Если этого не сделать, то ошибок при запуске не будет, но и бот также работать не будет.
     */
    public static void main( String[] args ) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramBotSecondApplication.class , args);
    }

}
