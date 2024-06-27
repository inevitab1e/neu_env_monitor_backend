package com.neu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NepGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepGatewayApplication.class, args);
    }

}
