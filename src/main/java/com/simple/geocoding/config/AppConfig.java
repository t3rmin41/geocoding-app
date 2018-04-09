package com.simple.geocoding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.simple.geocoding.service", "com.simple.geocoding.mapper", "com.simple.geocoding.jms"})
public class AppConfig {

}
