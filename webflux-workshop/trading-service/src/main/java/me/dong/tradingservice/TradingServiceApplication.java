package me.dong.tradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * annotation-based WebFlux app using a datastore, HTML views, and several browser-related technologies
 * -> https://bclozel.github.io/webflux-workshop/
 */
@SpringBootApplication
public class TradingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingServiceApplication.class, args);
    }
}
