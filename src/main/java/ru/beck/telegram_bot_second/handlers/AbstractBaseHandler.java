package ru.beck.telegram_bot_second.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.beck.telegram_bot_second.events.SendMessageEvent;

@RequiredArgsConstructor
public abstract class AbstractBaseHandler {
    private final ApplicationEventPublisher publisher;

    public abstract void handle( User from , String command );

    //todo мб файнал
    public void publish( SendMessage message ) {
        this.publisher.publishEvent(new SendMessageEvent(message));
    }

    public String getUsername( User from ) {
        String userName = from.getUserName();
        return from.getUserName() == null ? String.format("%s %s" , from.getFirstName() , from.getLastName()) : userName;
    }
}
