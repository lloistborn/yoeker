# yoeker
`@YoekerBot` As i called her Yoekie, a client for telegram-bot written on Java using Springboot 2.2.2 and Netty server.

Yoekie is listening to new message using `onUpdateReceived(Update update)` and convert the message into reactive-data-stream-context to make it posible handling message [reactively](https://www.reactivemanifesto.org/).

Here is sample how it is done by Yoekie
```java
  @Override
  public void onUpdateReceived(Update update) {

    Mono.just(update)
        .filter(Update::hasMessage)
        .filter(upd -> upd.getMessage().hasText())
        .flatMap(upd -> {
          String messageText = upd.getMessage().getText();
          long chatId = upd.getMessage().getChatId();

          return Mono.fromCallable(() ->
              execute(new SendMessage().setChatId(chatId).setText(messageText)))
              .doOnNext(data -> LOGGER.info("Sent message \"{}\" to {}", messageText, chatId))
              .doOnError(throwable -> LOGGER.error("Failed to send message \"{}\" to {} due to error: {}",
                  messageText, chatId, throwable.getMessage()));
        })
        .subscribe();
  }
```
