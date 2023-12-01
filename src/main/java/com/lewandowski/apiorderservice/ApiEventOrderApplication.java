package com.lewandowski.apiorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiEventOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiEventOrderApplication.class, args);
    }

}
