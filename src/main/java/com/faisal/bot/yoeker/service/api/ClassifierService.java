package com.faisal.bot.yoeker.service.api;

import com.faisal.bot.yoeker.entity.Intent;

public interface ClassifierService {
  Intent classify(String text);
}
