package com.learnreactiveprogramming.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebFluxTest
//@SpringBootTest
//@AutoConfigureWebTestClient
public class FluxControllerTest {

    @Autowired
    WebTestClient webClient ;

    @Test
    public void testFlux(){

        Flux<Integer> values = webClient.get().uri("/flux")
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
    public void testFlux2(){

        webClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .hasSize(4);
    }


    @Test
    public void testFlux3(){
        List<Integer> expectedIntegerList = List.of(1,2,3,4);
        EntityExchangeResult<List<Integer>> entityExchangeResult = webClient
                .get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();

        assertEquals(expectedIntegerList,entityExchangeResult.getResponseBody());


    }

    @Test
    public void testFlux4(){

        List<Integer> expectedIntegerList = List.of(1,2,3,4);
        webClient
                .get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith(
                        (response      ) -> {
                            assertEquals(expectedIntegerList, response.getResponseBody());
                        }
                );
    }

    @Test
    public void testFluxNDJSON(){


        Flux<Integer> values = webClient.get().uri("/fluxndjson")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(values)
                .expectNext(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 , 19 , 20)
                .verifyComplete();

    }

    @Test
    public void testFluxInfinit(){
        Flux<Integer> values = webClient.get().uri("/fluxinfinit")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(values)
                .expectNext(0)
                .expectNext(1)
                .expectNext(2)
                .thenCancel()
                .log()
                .verify();

    }


}
