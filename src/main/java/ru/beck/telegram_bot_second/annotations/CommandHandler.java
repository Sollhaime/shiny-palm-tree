package ru.beck.telegram_bot_second.annotations;

import org.springframework.stereotype.Component;
import ru.beck.telegram_bot_second.commands.Command;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Component
@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandHandler {
    Command[] command();
}
