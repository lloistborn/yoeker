package com.faisal.bot.yoeker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class YoekerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(YoekerBot.class);

	public static void main(String[] args) {
		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();

		ConfigurableApplicationContext context = SpringApplication.run(YoekerApplication.class, args);
		try {
			YoekerBot yoekerBot = context.getBean(YoekerBot.class);
			botsApi.registerBot(yoekerBot);
		} catch (TelegramApiException e) {
			LOGGER.error("Failed to register bot {}", e.getMessage());
		}
	}

}
