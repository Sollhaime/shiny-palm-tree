package ru.beck.telegram_bot_second.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.beck.telegram_bot_second.events.Event;
import ru.beck.telegram_bot_second.events.SendMessageEvent;
import ru.beck.telegram_bot_second.events.UpdateEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class BecksGreatBotV2 extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    @Getter
    private String botUsername;

    @Value("${telegram.bot.token}")
    @Getter
    private String botToken;

    private final ApplicationEventPublisher publisher;

    @Override
    public void onUpdateReceived( Update update ) {
        publisher.publishEvent(new UpdateEvent(update));
    }

    @EventListener(SendMessageEvent.class)
    public void sendMessage( Event<SendMessage> event ) {
        SendMessage message = event.get();
        try {
            execute(message);
            log.info("Executed '{}'" , message);
        } catch (TelegramApiException e) {
            log.error("Failed '{}'" , message);
        }
    }
}
