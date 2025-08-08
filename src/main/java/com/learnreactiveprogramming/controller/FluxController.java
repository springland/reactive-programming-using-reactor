package com.learnreactiveprogramming.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {

    @GetMapping("/flux")
    public Flux<Integer> flux(){

        return Flux.range( 1, 4).delayElements(Duration.ofSeconds(1)).log();
    }


    @GetMapping(value = "/fluxtextstream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> fluxTextStream(){

        return Flux.range( 1, 20).delayElements(Duration.ofSeconds(1)).log();
    }


    // This works for curl , not chrome
    // curl -N -H "Accept: application/x-ndjson" http://localhost:8080/fluxndjson
    @GetMapping(value = "/fluxndjson" , produces = MediaType.APPLICATION_NDJSON_VALUE)

    public Flux<Integer> fluxndjson(){

        return Flux.range( 1, 20).delayElements(Duration.ofSeconds(1)).log();
    }

    // This does not work for chrome as well , only for curl
    //curl -N -H "Accept: application/stream+json" http://localhost:8080/fluxstream
    @GetMapping(value = "/fluxstream" , produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> fluxStream(){

        return Flux.range( 1, 20).delayElements(Duration.ofSeconds(1)).log();


    }

    // curl -N -H "Accept: application/x-ndjson" http://localhost:8080/fluxinfinit
    @GetMapping(value = "/fluxinfinit" , produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Integer> fluxInfinit(){

        return Flux.<Integer , Integer>generate(
                () -> 0 ,
                (state , sink) -> {
                    sink.next( state);
                    return state +1;

                }
        ).delayElements(Duration.ofSeconds(1)).log();


       // return Flux.interval(Duration.ofSeconds(1)).map(Long::intValue).log();
    }


}
