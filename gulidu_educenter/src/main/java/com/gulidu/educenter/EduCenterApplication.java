package com.gulidu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.gulidu.educenter","com.guli.educommom"})
@EnableSwagger2

public class EduCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduCenterApplication.class, args);
    }
}
