package br.com.cams.config.exchange;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.service.RSocketServiceProxyFactory;
import org.springframework.stereotype.Component;

import br.com.cams.controller.exchange.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DeclarativeClientConfig {

	public void init(RSocketRequester rSocketRequester) {
		RSocketServiceProxyFactory factory = RSocketServiceProxyFactory.builder(rSocketRequester).build();
		MessageService service = factory.createClient(MessageService.class);

		Mono<String> response = service.sendMessage("Lokesh", Mono.just("Hello there!"));
		response.subscribe(message -> log.info("RSocket response : {}", message));
	}

}
