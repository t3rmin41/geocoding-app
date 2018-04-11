package com.simple.geocoding.app;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import com.simple.geocoding.config.AppConfig;
import com.simple.geocoding.config.WebClientConfig;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.util.XMLResponseParser;

@SpringBootApplication
@Import({AppConfig.class, WebClientConfig.class}) //, ActiveMqConfig.class})
public class GeocodingApp implements CommandLineRunner {
  
  private static final Logger logger = LoggerFactory.getLogger(GeocodingApp.class);
  
  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private XMLResponseParser xmlParser;
  
  //@Autowired
  //private CityReader cityReader;
  
  private String outputType = "xml";
  
  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(GeocodingApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.warn("Started geocoding app");
    //Thread readerThread = new Thread(cityReader);
    //readerThread.setName("CityReader");
    //readerThread.start();
    String url = WebClientConfig.URL_BASE+outputType+"?address=Sarande+Albania&key="+WebClientConfig.GOOGLE_API_KEY;
    //String soapUrl = "http://lb.lt/webservices/ExchangeRates/ExchangeRates.asmx?wsdl";
    String response = restTemplate.getForObject(url, String.class);
    Address address = xmlParser.parseAddressFromXMLResponse(response);
    logger.warn("{}", address);
    //logger.warn("{}", response);
  }
}