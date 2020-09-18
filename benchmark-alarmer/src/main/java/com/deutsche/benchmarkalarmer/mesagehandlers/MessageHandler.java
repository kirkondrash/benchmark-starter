package com.deutsche.benchmarkalarmer.mesagehandlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;

public interface MessageHandler {

    default boolean canHandle(Message message) {return false;}

    void handle(TelegramBot telegramBot, Message message);
}
