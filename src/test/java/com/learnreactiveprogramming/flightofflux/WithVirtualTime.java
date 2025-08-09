package com.learnreactiveprogramming.flightofflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class WithVirtualTime {

    @Test
    public void withVirtualTime(){


        //Flux<Integer>  flux = Flux.range(0 , 4).delayElements(Duration.ofHours(4));

        // withVirtualTime only works with Flux created in supplier
        StepVerifier.withVirtualTime(
                () -> Flux.range(0 , 4).delayElements(Duration.ofHours(4))
        ).expectSubscription()
                .thenAwait(Duration.ofDays(1))
                .expectNextCount(4)
                .verifyComplete();
     }
}
