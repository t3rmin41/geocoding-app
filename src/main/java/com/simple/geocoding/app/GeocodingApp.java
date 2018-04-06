package com.simple.geocoding.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.simple.geocoding.config.ClientConfig;

@SpringBootApplication
@Import({ClientConfig.class})
public class GeocodingApp implements CommandLineRunner {
  
  @Autowired
  private RestTemplate restTemplate;
  
  private static final Logger logger = LoggerFactory.getLogger(GeocodingApp.class);
  
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(GeocodingApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.warn("Hello World!");
    ResponseEntity<String> response = restTemplate.getForEntity(ClientConfig.URL_BASE+"?address=Sarande+Albania&key="+ClientConfig.GOOGLE_API_KEY, String.class);
    logger.warn("{}", response);
  }
}