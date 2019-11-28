package com.gulidu.educenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.gulidu.educenter.mapper")
public class EduConfig {
}
