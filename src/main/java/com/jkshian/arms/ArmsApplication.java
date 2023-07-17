package com.jkshian.arms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication
public class ArmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmsApplication.class, args);
    }


}
