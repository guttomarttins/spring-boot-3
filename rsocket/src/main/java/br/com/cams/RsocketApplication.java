package br.com.cams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;

import br.com.cams.config.birectional.FluxConfig;
import br.com.cams.config.event.EventConfig;
import br.com.cams.config.exchange.DeclarativeClientConfig;
import br.com.cams.config.requestandresponse.RequestRespondeConfig;
import br.com.cams.config.stream.FluxStreamConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RsocketApplication implements CommandLineRunner {
	
	@Autowired
	private FluxConfig fluxConfig;
	
	@Autowired
	private RSocketRequester.Builder requesterBuilder;
	
	@Autowired
	private EventConfig eventConfig;
	
	@Autowired
	private RequestRespondeConfig requestRespondeConfig;
	
	@Autowired
	private FluxStreamConfig fluxStreamConfig;
	
	@Autowired
	private DeclarativeClientConfig declarativeClientConfig;

	public static void main(String[] args) {
		SpringApplication.run(RsocketApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("create rSocket on localhost");
		RSocketRequester rSocketRequester = requesterBuilder.tcp("localhost", 7000);
		log.info("init Flux Bidirectional");
		fluxConfig.init(rSocketRequester);
		log.info("finished Flux Bidirectional");
		log.info("init Event Fire And Forget");
		eventConfig.init(rSocketRequester);
		log.info("finished Event Fire And Forget");
		log.info("init Request And Response");
		requestRespondeConfig.init(rSocketRequester);
		log.info("finished Request And Response");
		log.info("init Flux Stream");
		fluxStreamConfig.init(rSocketRequester);
		log.info("finished Flux Stream");
		log.info("init exchange");
		declarativeClientConfig.init(rSocketRequester);
		log.info("finished exchange");
	}

}
