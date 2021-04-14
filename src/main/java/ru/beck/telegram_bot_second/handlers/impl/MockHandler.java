package ru.beck.telegram_bot_second.handlers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.beck.telegram_bot_second.annotations.CommandHandler;
import ru.beck.telegram_bot_second.builder.MessageBuilder;
import ru.beck.telegram_bot_second.commands.Command;
import ru.beck.telegram_bot_second.handlers.AbstractBaseHandler;

import static ru.beck.telegram_bot_second.commands.Command.WEATHER;
@Slf4j
@CommandHandler(command = WEATHER)
public class MockHandler extends AbstractBaseHandler {
    public MockHandler( ApplicationEventPublisher publisher ) {
        super(publisher);
    }

    @Override
    public void handle( User from , String command ) {
        log.info("going to talk about weather");
        String username = getUsername(from);
        MessageBuilder builder = MessageBuilder.builder(from)
                .textLine("The weather is pretty good today, is it?")
                .textLine("Did you know that people talk about weather only when they dont know what else to talk about?")
                .textLine("%s - cool and rare name, dude.", username)
                .textLine("Ok, maybe, in fact, not so rare, but definitely very cool");
        publish(builder.build());
    }
}
