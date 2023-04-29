package br.com.cams.config.stream;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import br.com.cams.model.stream.StockPrice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FluxStreamConfig {
	
	public void init(RSocketRequester rSocketRequester) {
		
		String stockSymbol = "TESLA";
		
		rSocketRequester
			.route("stock/{symbol}", stockSymbol)
	        .retrieveFlux(StockPrice.class)
	        .doOnNext(stockPrice ->
	            log.info(
	                "Price of {} : {} (at {})",
	                stockPrice.getSymbol(),
	                stockPrice.getPrice(),
	                stockPrice.getTimestamp())
	        )
	        .subscribe();
	}

}
