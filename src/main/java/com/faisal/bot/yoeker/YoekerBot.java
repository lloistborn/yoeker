package com.faisal.bot.yoeker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class YoekerBot extends TelegramLongPollingBot {

  private static final Logger LOGGER = LoggerFactory.getLogger(YoekerApplication.class);

  @Override
  public void onUpdateReceived(Update update) {

    if (update.hasMessage() && update.getMessage().hasText()) {

      String messageText = update.getMessage().getText();
      long chatId = update.getMessage().getChatId();

      try {
        execute(new SendMessage()
            .setChatId(chatId)
            .setText(messageText));
        LOGGER.info("Sent message \"{}\" to {}", messageText, chatId);
      } catch (TelegramApiException e) {
        LOGGER.error("Failed to send message \"{}\" to {} due to error: {}", messageText, chatId,
            e.getMessage());
      }
    }
  }

  @Override
  public String getBotUsername() {
    return "YoekerBot";
  }

  @Override
  public String getBotToken() {
    return "1007489257:AAEKpAi1SBBMgsszqHIx3pqZy7JEs3CbWlw";
  }
}
