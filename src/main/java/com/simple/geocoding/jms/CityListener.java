package com.simple.geocoding.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.simple.geocoding.config.ClientConfig;

public class CityListener implements MessageListener {

  private static final Logger logger = LoggerFactory.getLogger(CityListener.class);
  
  @Autowired
  private RestTemplate restTemplate;
  
  private String outputType = "xml";
  
  @Override
  public void onMessage(Message message) {
    try {
      logger.warn("Received message from queue");
      if (message instanceof ObjectMessage) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        QueueCityMessage requestMessage = (QueueCityMessage) objectMessage.getObject();
        ResponseEntity<String> response = restTemplate.getForEntity(ClientConfig.URL_BASE+outputType+"?address="+requestMessage.getCity()+"+"+requestMessage.getCountry()+"&key="+ClientConfig.GOOGLE_API_KEY, String.class);
        logger.warn("{}", response);
      }
    } catch (JMSException e) {
      logger.error(e.getMessage());
    } catch (NullPointerException e) {
      logger.error(e.getMessage());
    }
  }

}
