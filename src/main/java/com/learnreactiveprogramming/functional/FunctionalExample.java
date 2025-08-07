package com.learnreactiveprogramming.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class FunctionalExample {
    public static void main(String[] args) {


        var names = List.of("Alex" , "John" , "Ben" , "Harry" );

        var filteredNames = names.stream().filter( s-> s.length() > 3).toList();
        log.info(filteredNames.toString());

    }
}
