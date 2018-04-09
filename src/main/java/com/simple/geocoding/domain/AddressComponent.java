package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class AddressComponent {

  @XmlElement(name="long_name")
  private String longName;
  @XmlElement(name="short_name")
  private String shortName;
  
  @XmlElementWrapper
  @XmlElement(name="type")
  private List<AddressType> types = new LinkedList<AddressType>();

  public AddressComponent() {}
  
  /*
  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public List<AddressType> getTypes() {
    return types;
  }
  /**/
  
}
