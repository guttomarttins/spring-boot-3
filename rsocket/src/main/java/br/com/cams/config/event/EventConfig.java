package br.com.cams.config.event;

import java.time.Instant;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import br.com.cams.model.fireandforget.Event;
import br.com.cams.model.fireandforget.Event.Type;

@Component
public class EventConfig {
	
	public void init(RSocketRequester rSocketRequester) {
		rSocketRequester
        .route("event")
        .data(new Event(11L, Type.ERROR, Instant.now()))
        .send()
        .subscribe();
	}

}
