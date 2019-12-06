package com.faisal.bot.yoeker;

import com.faisal.bot.yoeker.configuration.YoekerBotProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class YoekerBot extends TelegramLongPollingBot {

  private static final Logger LOGGER = LoggerFactory.getLogger(YoekerBot.class);

  @Autowired
  private YoekerBotProperties properties;

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
        LOGGER.error("Failed to send message \"{}\" to {} due to error: {}", messageText, chatId, e.getMessage());
      }
    }
  }

  @Override
  public String getBotUsername() {
    return properties.getUsername();
  }

  @Override
  public String getBotToken() {
    return properties.getToken();
  }
}
