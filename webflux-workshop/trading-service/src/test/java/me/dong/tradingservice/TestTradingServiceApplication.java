package me.dong.tradingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestTradingServiceApplication {

    @Bean
    @ServiceConnection MongoDBContainer mongoDbContainer() {
        return new MongoDBContainer("mongo:latest");
    }

    public static void main(String[] args) {
        SpringApplication.from(TradingServiceApplication::main).with(TestTradingServiceApplication.class).run(args);
    }
}
