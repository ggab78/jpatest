package com.gab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.gab"})
public class BeenConfig {

    @Autowired
    DBConfig dbConfig;

}
