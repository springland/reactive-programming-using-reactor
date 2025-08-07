package com.learnreactiveprogramming.imperative;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ImperativeExample {

    public static void main(String[] args) {

        var names = List.of("Alex" , "John" , "Ben" , "Harry" );

        var filteredNames = new ArrayList<String>() ;
        for(var name : names){
            if(name.length() > 3){
                filteredNames.add(name);
            }
        }

        for(var name: filteredNames){
            log.info(name);
        }
    }
}
