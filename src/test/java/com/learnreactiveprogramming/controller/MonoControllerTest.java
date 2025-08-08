package com.learnreactiveprogramming.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebFluxTest
//@AutoConfigureWebTestClient
public class MonoControllerTest {

    @Autowired
    WebTestClient webClient ;

    @Test
    public void testMono(){

        Flux<String> values =webClient.get().uri("/mono")
                .exchange()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(values)
                .expectNext("john")
                .verifyComplete();
    }

    @Test
    public void testMono2(){
        String expectedValue = "john";

        webClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith((response) -> {
                    assertEquals(expectedValue, response.getResponseBody());
                });

    }
}
