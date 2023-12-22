package me.dong.tradingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Component
public class UsersCommandLineRunner implements CommandLineRunner {

    private final TradingUserRepository tradingUserRepository;

    public UsersCommandLineRunner(TradingUserRepository tradingUserRepository) {
        this.tradingUserRepository = tradingUserRepository;
    }

    /*
    run()은 void를 return하기 때문에 blocking 구현이 필요
    repository에서 반환된 Flux에 대해 blockLast() 사용
    또한 Flux의 완료를 기다리는 Mono<Void>로 바꿀려면 then().block(Duration)를 사용
     */
    @Override
    public void run(String... args) throws Exception {
        List<TradingUser> users = Arrays.asList(
                new TradingUser("sdeleuze", "Sebastien Deleuze"),
                new TradingUser("snicoll", "Stephane Nicoll"),
                new TradingUser("rstoyanchev", "Rossen Stoyanchev"),
                new TradingUser("poutsma", "Arjen Poutsma"),
                new TradingUser("smaldini", "Stephane Maldini"),
                new TradingUser("simonbasle", "Simon Basle"),
                new TradingUser("violetagg", "Violeta Georgieva"),
                new TradingUser("bclozel", "Brian Clozel")
        );

        this.tradingUserRepository.insert(users).blockLast(Duration.ofSeconds(3));
    }
}
