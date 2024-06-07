package com.neu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NepSupervisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepSupervisorApplication.class, args);
    }

}
