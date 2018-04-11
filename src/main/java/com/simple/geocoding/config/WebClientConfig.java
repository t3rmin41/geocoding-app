package com.simple.geocoding.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

@Configuration
public class WebClientConfig {

  public static final String GOOGLE_API_KEY = "AIzaSyC3KKFDMXH9sa0UrpRg6nGHswMNJQ-1I9Y";
  public static final String URL_BASE = "https://maps.googleapis.com/maps/api/geocode/";

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
    //restTemplate.getMessageConverters().add(0, gsonHttpMessageConverter());
    //restTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    return restTemplate;
  }
  
  /*
  @Bean
  public GsonHttpMessageConverter gsonHttpMessageConverter() {
    GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter();
    List<MediaType> types = Arrays.asList(
        new MediaType("text", "html", Charset.forName("UTF-8")),
        new MediaType("application", "json", Charset.forName("UTF-8")),
        new MediaType("application", "*+json", Charset.forName("UTF-8")),
        new MediaType("application", "*xml", Charset.forName("UTF-8")),
        new MediaType("application", "*+xml", Charset.forName("UTF-8"))
    );
    messageConverter.setSupportedMediaTypes(types);
    return  messageConverter;
  }
  /**/
  
}
