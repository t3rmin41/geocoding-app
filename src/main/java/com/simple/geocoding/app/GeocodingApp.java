package com.simple.geocoding.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeocodingApp implements CommandLineRunner {
  
  private static final Logger logger = LoggerFactory.getLogger(GeocodingApp.class);
  
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(GeocodingApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.warn("Hello World!");
  }
}