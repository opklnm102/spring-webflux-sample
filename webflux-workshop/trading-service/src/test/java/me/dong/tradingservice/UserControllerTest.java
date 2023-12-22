package me.dong.tradingservice;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@WebFluxTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TradingUserRepository tradingUserRepository;

    @Test
    public void listUsers() {
        TradingUser ethan = new TradingUser("1", "ethan", "ethan kim");
        TradingUser andy = new TradingUser("2", "andy", "Andy kim");

        BDDMockito.given(this.tradingUserRepository.findAll())
            .willReturn(Flux.just(ethan, andy));

        this.webTestClient.get().uri("/users").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBodyList(TradingUser.class)
            .hasSize(2)
            .contains(ethan, andy);
    }

    @Test
    public void showUsers() {
        TradingUser ethan = new TradingUser("1", "ethan", "ethan kim");

        BDDMockito.given(this.tradingUserRepository.findByUserName("ethan"))
            .willReturn(Mono.just(ethan));

        this.webTestClient.get().uri("/users/ethan").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody(TradingUser.class)
            .isEqualTo(ethan);
    }
}