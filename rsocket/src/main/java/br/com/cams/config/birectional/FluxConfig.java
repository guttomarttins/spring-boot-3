package br.com.cams.config.birectional;

import java.time.Duration;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import br.com.cams.model.bidirectional.LoanDetails;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class FluxConfig {
	
	public void init(RSocketRequester rSocketRequester) {
		Flux<LoanDetails> lonaDetailsFlux = Flux.fromArray(
				        new LoanDetails[] { 
				        		new LoanDetails(100, 5, 1), 
				        		new LoanDetails(200, 7, 1),
						        new LoanDetails(300, 10, 1), 
						        new LoanDetails(400, 8, 1), 
						        new LoanDetails(600, 11, 1) 
						        })
				.delayElements(Duration.ofSeconds(2));

		rSocketRequester.route("check-loan-eligibility").data(lonaDetailsFlux).retrieveFlux(Boolean.class)
				.subscribe(result -> log.info("Loan eligibility : {}", result));
	}

}
