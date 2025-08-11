package com.learnreactiveprogramming.backpressure;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
public class BackPressureDemo {

    @Test
    public void backpressure() throws Exception{

        // use fluxinfinit
        // request 5 , then 3 , then cancel

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        Flux<Integer> flux = webClient.get().uri("/fluxinfinit")
                .retrieve()
                .bodyToFlux(Integer.class);

        flux.doOnNext(i -> log.info(" got : {}" , i))
                .log()
                .subscribe(new BackPressureSubscriber<Integer>());

        Thread.sleep(30000);
    }
}
