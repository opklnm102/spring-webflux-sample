package me.dong.stockquotes;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Component
public class QuoteHandler {

    private final Flux<Quote> quoteStream;

    public QuoteHandler(QuoteGenerator quoteGenerator) {
        // share() - ?
        this.quoteStream = quoteGenerator.fetchQuoteStream(Duration.ofMillis(1000)).share();
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello Spring!"));
    }

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(request.bodyToMono(String.class), String.class);
    }

    // 무한히 흐르는 stream
    public Mono<ServerResponse> streamQuotes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.quoteStream, Quote.class);
    }

    // 유한한 collection
    public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteStream.take(size), Quote.class);
    }
}
