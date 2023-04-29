package br.com.cams.controller.stream;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import br.com.cams.model.stream.StockPrice;
import reactor.core.publisher.Flux;

@Controller
public class StockPriceController {

  @MessageMapping("stock/{symbol}")
  public Flux<StockPrice> getStockPrice(@DestinationVariable("symbol") String symbol) {

    return Flux
        .interval(Duration.ofSeconds(1))
        .map(i -> {
          BigDecimal price = BigDecimal.valueOf(Math.random() * 10);
          return new StockPrice(symbol, price, Instant.now());
        });
  }
}
