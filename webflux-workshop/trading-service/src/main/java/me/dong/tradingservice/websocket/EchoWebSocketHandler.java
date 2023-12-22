package me.dong.tradingservice.websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.time.Duration;

import reactor.core.publisher.Mono;

/**
 * WebSocketHandler
 * - WebSocket session을 처리한다
 * <p>
 * Created by ethan.kim on 2018. 6. 25..
 */
public class EchoWebSocketHandler implements WebSocketHandler {

    // 1초의 delay 후 수신한 message echo
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive()
                .doOnNext(WebSocketMessage::retain)
                .delayElements(Duration.ofSeconds(1))
                .log());
    }
}
