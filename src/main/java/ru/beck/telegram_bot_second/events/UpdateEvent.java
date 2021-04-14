package ru.beck.telegram_bot_second.events;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateEvent extends Event<Update> {
    public UpdateEvent( Update object ) {
        super(object);
    }
}
