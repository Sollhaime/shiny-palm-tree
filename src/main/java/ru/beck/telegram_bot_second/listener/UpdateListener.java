package ru.beck.telegram_bot_second.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.beck.telegram_bot_second.events.Event;
import ru.beck.telegram_bot_second.events.UpdateEvent;
import ru.beck.telegram_bot_second.handlers.provider.HandlerProvider;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateListener {
    private final HandlerProvider provider;

    @EventListener(UpdateEvent.class)
    public void handleUpdate( Event<Update> event ) {
        Update update = event.get();
        try {
            User from;
            String text;
            if(isWithText(update)) {
                from = update.getMessage().getFrom();
                text = extractText(update);
            } else {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                from = callbackQuery.getFrom();
                text = callbackQuery.getData();
            }
            if(text != null && from != null) {
                provider.getHandlerFor(text).handle(from , text);
            } else {
                throw new IllegalArgumentException(update.toString());
            }
        } catch (IllegalArgumentException e) {
            log.error("Unsupported command '{}'" , e.getMessage());
        }
    }

    private boolean isWithText( Update update ) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private String extractText( Update update ) {
        return update.getMessage().getText();
    }
}
