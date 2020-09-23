package com.gab.service;

import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {


    public void printlnGreeting(String name){
        System.out.println("Hello "+name);
    }

}
