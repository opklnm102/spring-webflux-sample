package com.example.demo;

import com.example.demo.car.user.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Test {

    public void test() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        /*
        WebClient
            asynchroneous, non-blocking, reactive HTTP client

        RestClient
            WebClient와 유사한 API를 제공
            message converter, request factory, interceptor 등 RestTemplate의 다른 기본 구성 요소를 사용하는 HTTP client
         */

        RestClient restClient = RestClient.create();
        var result = restClient.get()
            .uri("http://localhost:8080")
            .retrieve()
            .body(String.class);

        var result1 = restClient.get()
            .uri("http://localhost:8080")
            .retrieve()
            .toEntity(String.class);

        result1.getStatusCode();
        result1.getHeaders();
        result1.getBody();

        var user = restClient.get()
            .uri("http://localhost:8080")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new RuntimeException(response.getStatusCode() + response.getHeaders().toString());
            })
            .body(User.class);

        // https://spring.io/blog/2023/07/13/new-in-spring-6-1-restclient/
    }
}
