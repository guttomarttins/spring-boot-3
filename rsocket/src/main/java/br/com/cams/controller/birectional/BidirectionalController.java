package br.com.cams.controller.birectional;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import br.com.cams.model.bidirectional.LoanDetails;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class BidirectionalController {
	
	@MessageMapping("check-loan-eligibility")
	  public Flux<Boolean> calculate(Flux<LoanDetails> loanDetails) {
	    return loanDetails
	        .doOnNext(ld -> log.info("Calculating eligibility:  {}", ld))
	        .map(ld -> {
	          if(ld.getRate() > 9)
	            return true;
	          else
	            return false;
	        });
	  }

}
