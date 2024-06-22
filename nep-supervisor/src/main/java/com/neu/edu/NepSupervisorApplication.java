package com.neu.edu;

import com.neu.edu.client.config.DefaultFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
public class NepSupervisorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepSupervisorApplication.class, args);
    }

}
