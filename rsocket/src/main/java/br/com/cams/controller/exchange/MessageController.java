package br.com.cams.controller.exchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class MessageController {

	@MessageMapping("greeting/{name}")
	public Mono<String> handleGreeting(@DestinationVariable("name") String name, Mono<String> greetingMono) {
		return greetingMono.doOnNext(greeting -> log.info("Received a greeting from {} : {}", name, greeting))
				.map(greeting -> "Hello " + name + "!");
	}
}
