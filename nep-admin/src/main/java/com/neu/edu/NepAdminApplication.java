package com.neu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NepAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepAdminApplication.class, args);
    }

}
