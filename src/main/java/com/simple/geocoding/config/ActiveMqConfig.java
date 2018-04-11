package com.simple.geocoding.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import com.simple.geocoding.jms.CityListener;
import com.simple.geocoding.jms.CityReader;

@Configuration
public class ActiveMqConfig {

  public static final String BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";
  public static final String CITY_QUEUE = "city.queue";
  
  
  @Bean(name = "jmsConnectionFactory")
  public ConnectionFactory jmsConnectionFactory() {
      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
      connectionFactory.setAlwaysSessionAsync(true);
      connectionFactory.setBrokerURL(BROKER_URL);
      return connectionFactory;
  }

  @Bean(name = "jmsTemplate")
  public JmsTemplate jmsTemplate() {
      JmsTemplate jmsTemplate = new JmsTemplate();
      jmsTemplate.setDefaultDestination(new ActiveMQQueue(BROKER_URL));
      jmsTemplate.setConnectionFactory(jmsConnectionFactory());
      return jmsTemplate;
  }

  @Bean
  public CityReader cityReader() {
      return new CityReader();
  }

  @Bean
  public CityListener cityListener() {
      return new CityListener();
  }

  @Bean
  public DefaultMessageListenerContainer messageListenerContainer() {
      DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
      messageListenerContainer.setConnectionFactory(jmsConnectionFactory());
      messageListenerContainer.setDestinationName(CITY_QUEUE);
      messageListenerContainer.setMessageListener(cityListener());
      return messageListenerContainer;
  }
  
  
}
