package com.simple.geocoding.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.simple.geocoding.service", "com.simple.geocoding.mapper", "com.simple.geocoding.jms", "com.simple.geocoding.util"})
public class AppConfig {

  @Bean
  public ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
      registrationBean.addUrlMappings("/h2-console/*");
      return registrationBean;
  }
  
}
