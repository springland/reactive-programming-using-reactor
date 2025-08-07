package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService  service = new FluxAndMonoGeneratorService();

    @Test
    public void namesFlux(){

        Flux<String> namesFlux = service.namesFlux();
        StepVerifier.create(namesFlux).expectNext(
                "Alex" , "John" , "Ben" ,"Harry")
                .verifyComplete();


        StepVerifier.create(namesFlux).expectNextCount(4)
                .verifyComplete();

        StepVerifier.create(namesFlux)
                .expectNext("Alex")
                .expectNext("John")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void namesFluxUppercase(){

        Flux<String> namesUppercase = service.namesFluxUppercase();

        StepVerifier.create(namesUppercase)
                .expectNext("ALEX" , "JOHN" , "BEN" , "HARRY")
                .verifyComplete();
    }

    @Test
    public void namesFluxFlatmap(){
        Flux<String> namesChar = service.namesFluxFlatmap();

        StepVerifier.create(namesChar)
                .expectNext("A" , "l" , "e" , "x" , "J" , "o" , "h" , "n" , "B" , "e" , "n" , "H" , "a" , "r" , "r", "y")
                .verifyComplete();
    }

    @Test
    public void namesFluxFlatmapAsync(){
        Flux<String> namesChar = service.namesFluxFlatmapAsync();

        StepVerifier.create(namesChar)
                .expectNext("A" , "l" , "e" , "x" , "J" , "o" , "h" , "n" , "B" , "e" , "n" , "H" , "a" , "r" , "r", "y")
                .verifyComplete();
    }

}
