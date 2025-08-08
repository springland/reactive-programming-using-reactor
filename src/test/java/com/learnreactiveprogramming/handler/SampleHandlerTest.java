package com.learnreactiveprogramming.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient

public class SampleHandlerTest {

    @Autowired
    WebTestClient webClient ;


    @Test
    public void testFlux(){

        Flux<Integer> values = webClient.get().uri("/handler/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(values)
                .expectNext(1 , 2  , 3 , 4)
                .verifyComplete();

    }

    @Test
    public void testMono(){

        Flux<String> values =webClient.get().uri("/handler/mono")
                .exchange()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(values)
                .expectNext("john")
                .verifyComplete();
    }

}
