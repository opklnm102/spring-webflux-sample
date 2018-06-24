package me.dong.tradingservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
public interface TradingUserRepository extends ReactiveMongoRepository<TradingUser, String> {

    Mono<TradingUser> findByUserName(String userName);
}
