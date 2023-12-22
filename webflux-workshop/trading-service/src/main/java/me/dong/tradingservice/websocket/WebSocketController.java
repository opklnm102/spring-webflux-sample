package me.dong.tradingservice.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Controller
public class WebSocketController {

    @GetMapping(path = "/websocket")
    public String websocket() {
        return "websocket";
    }

    /*
    WebFlux에는 functional reactive WebSocket client and server support 포함
    server side에 2가지 main component가 있다

    1. WebSocketHandlerAdpater
        설정된 WebSocketService로 위임하여 들어오는 request를 처리
    2. WebSocketHandler
        WebSocket session을 처리한다

    자세한건 - https://docs.spring.io/spring-framework/docs/5.0.6.RELEASE/spring-framework-reference/web-reactive.html#webflux-websocket
     */
}
