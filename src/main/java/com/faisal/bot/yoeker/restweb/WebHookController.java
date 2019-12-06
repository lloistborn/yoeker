package com.faisal.bot.yoeker.restweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebHookController {

  @RequestMapping("/webhook")
  @ResponseBody
  Mono<String> webhook() {
    return Mono.fromCallable(() -> "webhook connected");
  }
}
