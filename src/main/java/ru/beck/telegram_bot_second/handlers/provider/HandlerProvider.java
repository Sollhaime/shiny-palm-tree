package ru.beck.telegram_bot_second.handlers.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.beck.telegram_bot_second.annotations.CommandHandler;
import ru.beck.telegram_bot_second.handlers.AbstractBaseHandler;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class HandlerProvider {

    @Getter
    private final List<AbstractBaseHandler> handlers;

    public AbstractBaseHandler getHandlerFor( String text ) {
        return handlers.stream()
                .filter(handler -> handler.getClass()
                        .isAnnotationPresent(CommandHandler.class))
                .filter(handler -> Stream.of(handler.getClass()
                        .getAnnotation(CommandHandler.class)
                        .command())
                        .anyMatch(command -> command.toString().equalsIgnoreCase(extractCommand(text))))
                .findAny().orElseThrow(UnsupportedOperationException::new);

    }

    public String extractCommand( String text ) {
        return text.split(" ")[0];
    }
}
