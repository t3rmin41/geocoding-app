package com.simple.geocoding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.simple.geocoding.service", "com.simple.geocoding.mapper", "com.simple.geocoding.jms", "com.simple.geocoding.util", "com.simple.geocoding.repository"})
public class AppConfig {

}
