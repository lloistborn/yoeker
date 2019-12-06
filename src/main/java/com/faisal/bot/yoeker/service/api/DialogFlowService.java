package com.faisal.bot.yoeker.service.api;

import com.faisal.bot.yoeker.entity.Flow;

public interface DialogFlowService {

  Flow flowDeterminer(String messages);
}
