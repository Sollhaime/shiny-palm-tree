package ru.beck.telegram_bot_second.commands;

import lombok.Getter;

//todo мб добавить текстовый дескрипшн
public enum Command {
    START("does nothing"),
    HELP("get info about all commands available"),
    WEATHER("check the weather");

    @Getter
    private final String description;

    Command() {
        this.description = "";
    }

    Command( String description ) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "/" + this.name();
    }
}
