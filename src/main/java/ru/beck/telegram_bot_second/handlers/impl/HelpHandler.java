package ru.beck.telegram_bot_second.handlers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.beck.telegram_bot_second.annotations.CommandHandler;
import ru.beck.telegram_bot_second.builder.MessageBuilder;
import ru.beck.telegram_bot_second.commands.Command;
import ru.beck.telegram_bot_second.handlers.AbstractBaseHandler;

import java.util.List;

import static ru.beck.telegram_bot_second.commands.Command.HELP;

@Slf4j
@CommandHandler(command = HELP)
public class HelpHandler extends AbstractBaseHandler {
    @Value("${telegram.bot.name}")
    private String botUsername;
    private final List<AbstractBaseHandler> handlers;

    public HelpHandler( ApplicationEventPublisher publisher , List<AbstractBaseHandler> handlers ) {
        super(publisher);
        this.handlers = handlers;
    }

    @Override
    public void handle( User from , String message ) {
        log.info("preparing /HELP command");
        MessageBuilder builder = MessageBuilder.builder(from)
                .textLine("Hello, my name is %s! How can i help you?" , botUsername)
                .textLine("Here are all available commands");

        for(AbstractBaseHandler handler : handlers) {
            Command command = handler.getClass().getAnnotation(CommandHandler.class).command()[0];
            String description = command.getDescription();
            if(!description.isEmpty()) {
                builder.row().button(description , command);
            }
        }
        publish(builder.build());
    }
}
