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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.simple.geocoding.config.AppConfig;
import com.simple.geocoding.config.WebClientConfig;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.domain.AddressComponent;
import com.simple.geocoding.domain.AddressType;
import com.simple.geocoding.domain.Coords;
import com.simple.geocoding.domain.Geometry;

@SpringBootApplication
@Import({AppConfig.class, WebClientConfig.class}) //, ActiveMqConfig.class})
public class GeocodingApp implements CommandLineRunner {
  
  private static final Logger logger = LoggerFactory.getLogger(GeocodingApp.class);
  
  @Autowired
  private RestTemplate restTemplate;
  
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
    String soapUrl = "http://lb.lt/webservices/ExchangeRates/ExchangeRates.asmx?wsdl";
    String response = restTemplate.getForObject(url, String.class);
    //String response = restTemplate.postForObject(soapUrl, new GetListOfCurrencies(), String.class);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    InputSource is;
    try {
        builder = factory.newDocumentBuilder();
        is = new InputSource(new StringReader(response));
        Document doc = builder.parse(is);
        NodeList list = doc.getElementsByTagName("result");
        logger.warn("{}", list);
        logger.warn(list.item(0).getTextContent());
        Address address = new Address();
        for (int i = 0; i < list.item(0).getChildNodes().getLength(); i++) {
          if (null != list.item(0).getChildNodes().item(i).getAttributes()) {
            if ("type".equals(list.item(0).getChildNodes().item(i).getNodeName())) {
              address.getTypes().add(new AddressType().setType(list.item(0).getChildNodes().item(i).getChildNodes().item(0).getNodeValue()));
            }
            if ("place_id".equals(list.item(0).getChildNodes().item(i).getNodeName())) {
              address.setPlaceId(list.item(0).getChildNodes().item(i).getChildNodes().item(0).getNodeValue());
            }
            if ("formatted_address".equals(list.item(0).getChildNodes().item(i).getNodeName())) {
              address.setFormattedAddress(list.item(0).getChildNodes().item(i).getChildNodes().item(0).getNodeValue());
            }
            if ("address_component".equals(list.item(0).getChildNodes().item(i).getNodeName())) {
              NodeList compNodes = list.item(0).getChildNodes().item(i).getChildNodes();
              AddressComponent component = new AddressComponent();
              for (int j = 0; j < list.item(0).getChildNodes().item(i).getChildNodes().getLength(); j++) {
                if ("long_name".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  component.setLongName(list.item(0).getChildNodes().
                                             item(i).getChildNodes().
                                             item(j).getChildNodes().item(0).getNodeValue());
                }
                if ("short_name".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  component.setShortName(list.item(0).getChildNodes().
                                             item(i).getChildNodes().
                                             item(j).getChildNodes().item(0).getNodeValue());
                }
                if ("type".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  component.getTypes().add(new AddressType().setType(list.item(0).getChildNodes().
                                                                          item(i).getChildNodes().
                                                                          item(j).getChildNodes().item(0).getNodeValue()));
                }
              }
              address.getComponents().add(component);
            }
            if ("geometry".equals(list.item(0).getChildNodes().item(i).getNodeName())) {
              Geometry geometry = new Geometry();
              for (int j = 0; j < list.item(0).getChildNodes().item(i).getChildNodes().getLength(); j++) {
                if ("location_type".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  geometry.setLocationType(list.item(0).getChildNodes().
                                                item(i).getChildNodes().
                                                item(j).getChildNodes().item(0).getNodeValue());
                }
                if ("location".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  Coords locationCoords = new Coords();
                  locationCoords.setType("location");
                  for (int k = 0; k < list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
                    NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                  }
                  geometry.setLocation(locationCoords);
                }
                if ("viewport".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  for (int k = 0; k < list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
                    if ("southwest".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords southwest = new Coords();
                      southwest.setType("southwest");
                      geometry.getViewport().add(southwest);
                    }
                    if ("northeast".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords northeast = new Coords();
                      northeast.setType("northeast");
                      geometry.getViewport().add(northeast);
                    }
                  }
                }
                if ("bounds".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  for (int k = 0; k < list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
                    if ("southwest".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords southwest = new Coords();
                      southwest.setType("southwest");
                      geometry.getBounds().add(southwest);
                    }
                    if ("northeast".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords northeast = new Coords();
                      northeast.setType("southwest");
                      geometry.getBounds().add(northeast);
                    }
                  }
                }
              }
              address.setGeometry(geometry);
            }
          }
        }
        logger.warn("{}", address);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      logger.error("{}", e);
    }
    logger.warn("{}", response);
  }
}