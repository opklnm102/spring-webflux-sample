package me.dong.stockquotes;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

/**
 * Created by ethan.kim on 2018. 6. 23..
 */
@Component
public class QuoteGenerator {

    private final MathContext mathContext = new MathContext(2);

    private final Random random = new Random();

    private final List<Quote> prices = new ArrayList<>();

    public QuoteGenerator() {
        prices.add(new Quote("CTXS", 82.26));
        prices.add(new Quote("DELL", 63.74));
        prices.add(new Quote("GOOG", 847.24));
        prices.add(new Quote("MSFT", 65.11));
        prices.add(new Quote("ORCL", 45.71));
        prices.add(new Quote("RHT", 84.29));
        prices.add(new Quote("VMW", 92.21));
    }

    public Flux<Quote> fetchQuoteStream(Duration period) {

        // We want to emit quotes with a specific period to do so, we create a Flux.interval
        return Flux.interval(period)
                // In case of back-pressure, drop events
                .onBackpressureDrop()
                // For each tick, generate a list of quotes
                .map(this::generateQuotes)
                // flatten that List<Quote> into a Flux<Quote>
                .flatMapIterable(quotes -> quotes)
                .log("me.dong.stockquotes");
    }

    /**
     * Create quotes for all tickers at a single instant
     *
     * @param interval
     * @return
     */
    private List<Quote> generateQuotes(long interval) {
        final Instant now = Instant.now();
        return prices.stream()
                .map(baseQutoe -> {
                    BigDecimal priceChange = baseQutoe.getPrice()
                            .multiply(new BigDecimal(0.05 * this.random.nextDouble()), this.mathContext);

                    Quote result = new Quote(baseQutoe.getTicker(), baseQutoe.getPrice().add(priceChange));

                    result.setInstant(now);
                    return result;
                })
                .collect(Collectors.toList());
    }

    /*
    WebFlux는 2가지 기반으로 구성할 수 있다
    1. functional
    2. annotation based

    ## functional

    HTTP request가 오면 `HandlerFunction`이 처리해서 Mono<ServerResponse>를 반환
    대응되는건 @Controller

    route를 시켜야하는데 그건 `RouterFunction`
    ServerRequest를 받아서 Mono<HandlerFunction>을 반환
    request의 특정 경로와 일치하는 HandlerFunction.
    일치하지 않으면 empty Mono 반환
    대응되는건 @RequestMapping
    자세한건 여길보자 - https://docs.spring.io/spring-framework/docs/5.0.6.RELEASE/spring-framework-reference/web.html#mvc-ann-async-vs-webflux






     */
}
