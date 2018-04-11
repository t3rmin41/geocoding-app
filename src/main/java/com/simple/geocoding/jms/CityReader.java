package com.simple.geocoding.jms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import com.simple.geocoding.config.ActiveMqConfig;

public class CityReader implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(CityReader.class);
  
  @Autowired
  private JmsTemplate jmsTemplate;
  
  private String datasourceFilePath = "";
  
  private String messageQueue;
  
  @PostConstruct
  public void initQueue() {
    this.messageQueue = ActiveMqConfig.CITY_QUEUE;
  }
  
  @Override
  public void run() {
    setDatasourceFile("./src/main/resources/500_europe_cities.csv");
    readFromDatasource();
  }

  private void setDatasourceFile(String path) {
    this.datasourceFilePath = path;
  }

  private void readFromDatasource() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(datasourceFilePath));
      String line;
      int i = 0;
      logger.warn("Start reading the datasource");
      while ((line = br.readLine()) != null) {
        String[] dataLine = line.split(";");
        if (i > 0 ) {
          putToQueue(new QueueCityMessage().setCity(dataLine[0]).setCountry(dataLine[1]));
        }
        i++;
      }
    } catch (IOException e) {
      logger.error(e.getLocalizedMessage());
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        logger.error(e.getLocalizedMessage());
      }
    }
    //close queue session when done
//    try {
//        session.close();
//    } catch (JMSException e) {
//        logger.error(e.getLocalizedMessage());
//    }
  }

  private void putToQueue(QueueCityMessage message) {
    jmsTemplate.send(messageQueue, new MessageCreator(){
      public Message createMessage(Session session) throws JMSException {
        ObjectMessage objectMessage = session.createObjectMessage(message);
        return objectMessage;
      }
    });
    logger.warn("Sent message to queue " + messageQueue);
  }

}
