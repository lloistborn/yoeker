package com.faisal.bot.yoeker.service.impl;

import com.faisal.bot.yoeker.entity.Flow;
import com.faisal.bot.yoeker.service.api.DialogFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogFlowServiceImpl implements DialogFlowService {

  private NLPManager nlpManager;

  @Autowired
  public DialogFlowServiceImpl(NLPManager nlpManager) {
    this.nlpManager = nlpManager;
  }

  @Override
  public Flow flowDeterminer(String messages) {

    nlpManager.tag(messages);
    nlpManager.classify(messages);

    return new Flow();
  }
}
