package com.cxz.aopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(AopdemoApplication.class, args);


    }

}
