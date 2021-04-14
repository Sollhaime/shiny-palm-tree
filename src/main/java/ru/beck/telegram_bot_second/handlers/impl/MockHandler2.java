package ru.beck.telegram_bot_second.handlers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.beck.telegram_bot_second.annotations.CommandHandler;
import ru.beck.telegram_bot_second.builder.MessageBuilder;
import ru.beck.telegram_bot_second.commands.Command;
import ru.beck.telegram_bot_second.handlers.AbstractBaseHandler;

import static ru.beck.telegram_bot_second.commands.Command.START;
@Slf4j
@CommandHandler(command = START)
public class MockHandler2 extends AbstractBaseHandler {
    public MockHandler2( ApplicationEventPublisher publisher ) {
        super(publisher);
    }

    @Override
    public void handle( User from , String command ) {
        log.info("does nothing");
        MessageBuilder builder = MessageBuilder.builder(from).textLine("really does nothing, dude");
        publish(builder.build());
    }
}
