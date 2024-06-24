package com.neu.edu;

import com.neu.edu.client.config.DefaultFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class, basePackages = {"com.neu.edu.client.client"})
@EnableTransactionManagement
public class NepGridApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepGridApplication.class, args);
    }

}
