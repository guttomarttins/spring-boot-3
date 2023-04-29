package br.com.cams.controller.event;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import br.com.cams.model.fireandforget.Event;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class EventController {

  @MessageMapping("event")
  public Mono<Void> setAlert(Mono<Event> alertMono) {
    return alertMono
        .doOnNext(event ->
            log.info("Event Id '{}' occurred of type '{}' at '{}'",
                event.getId(),
                event.getType(),
                event.getTimestamp())
        )
        .thenEmpty(Mono.empty());
  }
}