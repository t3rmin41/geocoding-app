package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Address {

  //@XmlElement(name = "place_id")
  private String placeId;
  //@XmlElement(name = "formatted_address")
  private String formattedAddress;
  
  //@XmlElementWrapper
  //@XmlElement(name = "address_component")
  private List<AddressComponent> components = new LinkedList<AddressComponent>();
  
  //@XmlElementWrapper
  //@XmlElement(name = "type")
  private List<AddressType> types = new LinkedList<AddressType>();

  //@XmlElement(name = "geometry")
  private Geometry geometry;
  
  public Address() {}
  
  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }


  public List<AddressComponent> getComponents() {
    return components;
  }

  public List<AddressType> getTypes() {
    return types;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }
  /**/
  
  @Override
  public String toString() {
    String info = "";
    info = "[Address : placeId="+placeId+"; formattedAddress="+formattedAddress+"; components={";
    for (AddressComponent component : components) {
      info += component;
    }
    info += "}; types={";
    for (AddressType type : types) {
      info += type;
    }
    info += "}; geometry="+geometry+"]";
    return info;
  }
  
}
