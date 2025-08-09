package com.learnreactiveprogramming.flightofflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
public class SchedulerDemo {


    List<String>  names = List.of("Mike" , "John" , "Peter" , "Joe");

    private String  getName(int index){


        log.info(" Get index {} name {} thread {}" , index , names.get(index) , Thread.currentThread().getName());
        return names.get(index);
    }
    @Test
    public void publishOn(){
        Scheduler scheduler = Schedulers.boundedElastic();
        Flux<Integer> flux = Flux.range(0 , 4);


        // both map and subscribe are on Test worker thread
        flux.map(i -> getName(i)).subscribe( i -> log.info("receive value  {} on  {}" , i , Thread.currentThread().getName()));

        // map is still on Test worker thread , subscribe changes to bounced elastic
        flux.map(i -> getName(i)).publishOn(scheduler).subscribe(i -> log.info("publish on receive value {} on {}" , i , Thread.currentThread().getName()));

   //     flux.publishOn(scheduler).
   //             doOnNext( i -> log.info("doOnNext  receive value {} on {}" , i , Thread.currentThread().getName()))
   //             .subscribe();
    }
    @Test
    public void subscribeOn(){

        Scheduler scheduler = Schedulers.boundedElastic();
        Flux<Integer> flux = Flux.range(0 , 4);


        // both map and subscribe are on Test worker thread
        flux.map(i -> getName(i)).subscribe( i -> log.info("receive value  {} on  {}" , i , Thread.currentThread().getName()));

        // both map and subscribe changes to bounced elastic
        flux.map(i -> getName(i)).subscribeOn(scheduler).subscribe(i -> log.info("publish on receive value {} on {}" , i , Thread.currentThread().getName()));

    }


}
