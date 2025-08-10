package com.learnreactiveprogramming.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
@RestController
public class DebugController {

    @GetMapping("/debug/error")
    public Flux<String> error(){

        // It does not tell how the error was created
        // only knows the Flux throws an error
        return Flux.just("John" , "Peter")
                .concatWith(

                        Mono.error(new RuntimeException(" This is an error"))
                );

    }

    @GetMapping("/debug/hook")
    public Flux<String> onOperatorDebug(){

        // This one has better information how it failed
        // log shows Mono.error is called
        Hooks.onOperatorDebug();

        Flux<String>  flux =  Flux.just("John" , "Peter")
                .concatWith(
                        Mono.error(new RuntimeException(" This is an error"))
                );

        Hooks.resetOnOperatorDebug();
        return flux;

    }

    @GetMapping("/debug/checkpoint")
    public Flux<String> checkpoint(){

        /**
         Error has been observed at the following site(s):
         *__checkpoint ? Check point C
         |_ checkpoint ? Handler com.learnreactiveprogramming.controller.DebugController#checkpoint() [DispatcherHandler]
         *__checkpoint ? HTTP GET "/debug/checkpoint" [ExceptionHandlingWebHandler]
         */
        Flux<String>  flux =  Flux.just("John" , "Peter")
                .checkpoint("Check point A")
                .concatWith(Mono.just("Mike"))
                .checkpoint("Check point B" , false)
                .concatWith(
                        Mono.error(new RuntimeException(" This is an error"))
                )
                .checkpoint("Check point C" );

        return flux ;

    }
}
