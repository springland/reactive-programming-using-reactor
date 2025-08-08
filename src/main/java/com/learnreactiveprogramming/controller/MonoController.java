package com.learnreactiveprogramming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {

    @GetMapping("/mono")
    public Mono<String> mono(){

        return Mono.just("john");
    }
}
