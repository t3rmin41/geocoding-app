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
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import com.simple.geocoding.config.WebClientConfig;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.service.AddressService;
import com.simple.geocoding.util.XMLResponseParser;

public class CityListener implements MessageListener {

  private static final Logger logger = LoggerFactory.getLogger(CityListener.class);
  
  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private AddressService addressService;
  
  @Autowired
  private XMLResponseParser xmlParser;
  
  private String outputType = "xml";
  
  @Override
  public void onMessage(Message message) {
    try {
      logger.warn("Received message from queue");
      if (message instanceof ObjectMessage) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        QueueCityMessage requestMessage = (QueueCityMessage) objectMessage.getObject();
        String response = restTemplate.getForObject(WebClientConfig.URL_BASE+outputType+"?address="+requestMessage.getCity()+"+"+requestMessage.getCountry()+"&key="+WebClientConfig.GOOGLE_API_KEY, String.class);
        Address address = xmlParser.parseAddressFromXMLResponse(response);
        logger.warn("{}", address);
        addressService.saveAddress(address);
      }
    } catch (JMSException e) {
      logger.error(e.getMessage());
    } catch (NullPointerException e) {
      logger.error(e.getMessage());
    }
  }

}
