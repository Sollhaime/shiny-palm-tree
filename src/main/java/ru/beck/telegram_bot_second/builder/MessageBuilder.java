package ru.beck.telegram_bot_second.builder;

import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.beck.telegram_bot_second.commands.Command;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

//todo Остановился здесь. Реализовал абстракт хэндлера,
// у него паблишер и публикует он, возможно метод следует сделать финальным.
// Начал описывать ХэлпХэндлера и появилась необходимость в гибком билдере мессаджа.
// Тут нужно сделать возможность сетить текст и кнопки налету и отдавать билдом СендМессадж.
// Сейчас, когда приходит апдейт мессадж публикуется апдейт ивент,
// который слушает лиснер держащий хэндлер провайдера. Провайдер по тексту отыскивает нужного хэндлера
// и делегирует ему работу, результатом которой должен вызов паблиша с ивентом СендИвентМессадж(билдер.билд())
public class MessageBuilder {
    @Setter
    private String chatId;
    private final StringBuilder stringBuilder = new StringBuilder();
    private List<InlineKeyboardButton> buttonRow = null;
    private List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

    public static MessageBuilder builder( User user ) {
        return builder(String.valueOf(user.getId()));
    }

    public static MessageBuilder builder( String chatId ) {
        MessageBuilder builder = new MessageBuilder();
        builder.setChatId(chatId);
        return builder;
    }

    public static MessageBuilder builder( int chatId ) {
        return builder(String.valueOf(chatId));
    }

    public MessageBuilder textLine( String format , Object... args ) {
        stringBuilder.append(format(format , args));
        return textLine();
    }

    private MessageBuilder textLine() {
        stringBuilder.append(format("%n"));
        return this;
    }

    private void addRowToKeyboard() {
        if(buttonRow != null) {
            keyboard.add(buttonRow);
        }
    }

    public MessageBuilder row() {
        addRowToKeyboard();
        buttonRow = new ArrayList<>();
        return this;
    }

    public MessageBuilder button( String text , Command command ) {
        buttonRow.add(
                new InlineKeyboardButton()
                        .setText(text)
                        .setCallbackData(command.toString())
        );
        return this;
    }

    public SendMessage build() {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .enableMarkdown(true)
                .setText(stringBuilder.toString());
        addRowToKeyboard();
        if(!keyboard.isEmpty()) {
            sendMessage.setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(keyboard));
        }
        return sendMessage;
    }

}
