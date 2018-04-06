package com.simple.geocoding.config;

import java.nio.charset.Charset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

  public static final String GOOGLE_API_KEY = "AIzaSyC3KKFDMXH9sa0UrpRg6nGHswMNJQ-1I9Y";
  public static final String URL_BASE = "https://maps.googleapis.com/maps/api/geocode/json";

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return restTemplate;
  }
  
}
