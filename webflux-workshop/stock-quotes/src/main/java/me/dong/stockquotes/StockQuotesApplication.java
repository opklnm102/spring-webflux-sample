package me.dong.stockquotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * functional WebFlux app which streams stock quotes
 * -> https://bclozel.github.io/webflux-workshop/
 */
@SpringBootApplication
public class StockQuotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQuotesApplication.class, args);
    }
}
