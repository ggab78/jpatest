package com.gab.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerImpl implements Manager {

    private final Greeting greeting;


    public void println(String name) {
        greeting.printlnGreeting(name);
    }
}
