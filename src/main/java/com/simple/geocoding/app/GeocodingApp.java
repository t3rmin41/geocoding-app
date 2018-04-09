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
import com.simple.geocoding.config.ActiveMqConfig;
import com.simple.geocoding.config.AppConfig;
import com.simple.geocoding.config.ClientConfig;
import com.simple.geocoding.jms.CityReader;

@SpringBootApplication
@Import({AppConfig.class, ClientConfig.class, ActiveMqConfig.class})
public class GeocodingApp implements CommandLineRunner {
  
  private static final Logger logger = LoggerFactory.getLogger(GeocodingApp.class);
  
  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private CityReader cityReader;
  
  private String outputType = "xml";
  
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(GeocodingApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.warn("Started geocoding app");
    Thread readerThread = new Thread(cityReader);
    readerThread.setName("CityReader");
    readerThread.start();
  }
}