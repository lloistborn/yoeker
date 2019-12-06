package com.faisal.bot.yoeker.service.impl;

import com.faisal.bot.yoeker.entity.Intent;
import com.faisal.bot.yoeker.outbound.api.ClassifierOutboundClient;
import com.faisal.bot.yoeker.outbound.api.TaggerOutboundClient;
import com.faisal.bot.yoeker.service.api.ClassifierService;
import com.faisal.bot.yoeker.service.api.TaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NLPManager implements ClassifierService, TaggerService {

  private TaggerOutboundClient taggerOutboundClient;
  private ClassifierOutboundClient classifierOutboundClient;

  @Autowired
  public NLPManager(TaggerOutboundClient taggerOutboundClient,
      ClassifierOutboundClient classifierOutboundClient) {
    this.taggerOutboundClient = taggerOutboundClient;
    this.classifierOutboundClient = classifierOutboundClient;
  }

  @Override
  public Intent classify(String text) {
    return null;
  }

  @Override
  public String tag(String text) {
    return null;
  }
}
