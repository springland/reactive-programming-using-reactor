package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux(){


        var names = List.of("Alex" , "John" , "Ben" , "Harry");
        return Flux.fromIterable(names).log();
        //return Flux.just("Alex" , "John" , "Ben" , "Harry" );
    }

    public Flux<String> namesFluxUppercase(){

        return namesFlux().map(String::toUpperCase).log() ;
    }

    public Flux<String> namesFluxFlatmap(){

        return namesFlux().flatMap( n -> Flux.fromArray(n.split(""))).log();
    }

    public Flux<String> namesFluxFlatmapAsync(){

        int delay = new Random().nextInt(1000);
        Random random = new Random();
        return namesFlux().flatMap( n -> Flux.fromArray(n.split(""))).delayElements(
                Duration.ofMillis(delay)
        ).log();
    }
    public Mono<String> nameMono(){

        return Mono.just("Alex").log();
    }
    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
        service.namesFlux().subscribe( n -> log.info("Flux name is {} " ,n));

        service.nameMono().subscribe( n -> log.info("Mono name is {} " ,n));
    }
}
