package me.dong.tradingservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Controller
public class QuotesController {

    @GetMapping(path = "/quotes")
    public String quotes() {
        return "quotes";
    }

    @GetMapping(path = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> quotesStream() {
        return WebClient.create("http://localhost:9000")
                .get()
                .uri("/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class)
                // 해당 페이지에 연결하는 브라우저마다 quotes를 요청할 필요가 없으므로 Flux.share()를 사용
                .share()
                .log("me.dong.tradingservice");
    }
}
