package br.com.cams.config.requestandresponse;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestRespondeConfig {
	
	public void init(RSocketRequester rSocketRequester) {
		rSocketRequester
        	.route("greeting/{name}", "Lokesh")
        	.data("Hello there!")
        	.retrieveMono(String.class)
        	.subscribe(response -> log.info("RSocket response : {}", response));
	}

}
