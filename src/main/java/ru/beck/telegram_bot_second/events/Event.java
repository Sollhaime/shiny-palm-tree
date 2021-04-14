package ru.beck.telegram_bot_second.events;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Event<T> {
    private final T object;

    public T get() {
        return object;
    }
}
