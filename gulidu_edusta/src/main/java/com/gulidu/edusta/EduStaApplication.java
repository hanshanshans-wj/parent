package com.gulidu.edusta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@EnableFeignClients
@EnableEurekaClient
@ComponentScan(basePackages = {"com.gulidu.edusta","com.guli.educommom"})
@SpringBootApplication
public class EduStaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduStaApplication.class, args);
    }
}
