package com.simple.geocoding.util;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.domain.AddressComponent;
import com.simple.geocoding.domain.AddressType;
import com.simple.geocoding.domain.Coords;
import com.simple.geocoding.domain.Geometry;

@Service
public class XMLResponseParserImpl implements XMLResponseParser {

  private static final Logger logger = LoggerFactory.getLogger(XMLResponseParserImpl.class);
  
  @Override
  public Address parseAddressFromXMLResponse(String xml) {
    Address address = new Address();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    InputSource is;
    try {
        builder = factory.newDocumentBuilder();
        is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        NodeList list = doc.getElementsByTagName("result");
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
                  //NodeList coordsNodeList = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes();
                  Coords locationCoords =  getCoordsFromNodeList(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes());
                  locationCoords.setType("location");
                  geometry.setLocation(locationCoords);
                }
                if ("viewport".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  for (int k = 0; k < list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
                    if ("southwest".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      //NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords southwest = getCoordsFromNodeList(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes());
                      southwest.setType(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName());
                      geometry.getViewport().add(southwest);
                    }
                    if ("northeast".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      //NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords northeast = getCoordsFromNodeList(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes());
                      northeast.setType(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName());
                      geometry.getViewport().add(northeast);
                    }
                  }
                }
                if ("bounds".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getNodeName())) {
                  for (int k = 0; k < list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().getLength(); k++) {
                    if ("southwest".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      //NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords southwest = getCoordsFromNodeList(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes());
                      southwest.setType(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName());
                      geometry.getBounds().add(southwest);
                    }
                    if ("northeast".equals(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName())) {
                      //NodeList coords = list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes();
                      Coords northeast = getCoordsFromNodeList(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getChildNodes());
                      northeast.setType(list.item(0).getChildNodes().item(i).getChildNodes().item(j).getChildNodes().item(k).getNodeName());
                      geometry.getBounds().add(northeast);
                    }
                  }
                }
              }
              address.setGeometry(geometry);
            }
          }
        }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      logger.error("{}", e);
    }
    return address;
  }

  private Coords getCoordsFromNodeList(NodeList list) {
    Coords coords = new Coords();
    for (int i = 0; i < list.getLength(); i++) {
      if ("lng".equals(list.item(i).getNodeName())) {
        coords.setLongitude(new Double(list.item(i).getChildNodes().item(0).getNodeValue()));
      }
      if ("lat".equals(list.item(i).getNodeName())) {
        coords.setLatitude(new Double(list.item(i).getChildNodes().item(0).getNodeValue()));
      }
    }
    return coords;
  }
  
}
