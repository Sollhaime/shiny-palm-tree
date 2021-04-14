package ru.beck.telegram_bot_second.events;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageEvent extends Event<SendMessage> {
    public SendMessageEvent( SendMessage object ) {
        super(object);
    }
}
