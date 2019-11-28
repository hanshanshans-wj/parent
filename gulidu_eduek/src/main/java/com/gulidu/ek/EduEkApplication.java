package com.gulidu.ek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EduEkApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduEkApplication.class, args);
    }
}
