package com.gulidu.edusta.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.gulidu.edusta.mapper")
public class EduConfig {
}
