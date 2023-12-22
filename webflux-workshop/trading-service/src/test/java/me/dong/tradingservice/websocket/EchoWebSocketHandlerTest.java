package me.dong.tradingservice.websocket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.StandardWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Integration tests with WebSocketClient
 * - Spring WebFlux에 포함된 WebSocketClient를 사용해 WebSocket endpoint를 test할 수 있다
 * - WebSocket endpoint가 올바르게 동작하는지 확인
 * <p>
 * Created by ethan.kim on 2018. 6. 25..
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EchoWebSocketHandlerTest {

    @LocalServerPort
    private String port;

    @Test
    public void test() throws Exception {
        int count = 4;
        Flux<String> input = Flux.range(1, count).map(index -> "msg-" + index);
        ReplayProcessor<Object> output = ReplayProcessor.create(count);

        WebSocketClient client = new StandardWebSocketClient();
        client.execute(getUrl("/websocket/echo"),
                session -> session
                    .send(input.map(session::textMessage))
                    .thenMany(session.receive().take(count).map(WebSocketMessage::getPayloadAsText))
                    .subscribeWith(output)
                    .then())
            .block(Duration.ofMillis(5000));

        assertThat(input.collectList().block(Duration.ofMillis(5000))).isEqualTo(output.collectList().block(Duration.ofMillis(5000)));
    }

    protected URI getUrl(String path) throws URISyntaxException {
        return new URI("ws://localhost:" + this.port + path);
    }
}
