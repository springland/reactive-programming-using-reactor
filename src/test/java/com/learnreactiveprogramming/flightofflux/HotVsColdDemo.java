package com.learnreactiveprogramming.flightofflux;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 *  Hot Flux vs cold Flux
 */
@Slf4j
public class HotVsColdDemo {
    @Test
    public void coldDemo(){

        /**
         *  Cold flux is stateless , each subscription gets all events
         */

        Flux<Integer>  flux = Flux.range(1 , 5);

        flux.subscribe( i -> log.info(" first subscribe {}" , i));

        flux.subscribe( i -> log.info(" second subscribe {}" , i));

    }

    @Test
    public void hotDemo() throws Exception{

        /**
         * Hot flux shares a state ,
         * second subscribe only sees the elements after it connects
         *
         */

        // turn it to a hot flux
        ConnectableFlux<Integer>  cflux = Flux.range(1 , 5).delayElements(Duration.ofMillis(500)).publish();
        cflux.subscribe( i -> log.info(" first subscribe {}" , i));
        cflux.connect();
        Thread.sleep(2000L);
        cflux.subscribe( i -> log.info(" second subscribe {}" , i));
        Thread.sleep(2000L);
    }
}
