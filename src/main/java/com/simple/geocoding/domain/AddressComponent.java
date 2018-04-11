package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class AddressComponent {

  //@XmlElement(name="long_name")
  private String longName;
  //@XmlElement(name="short_name")
  private String shortName;
  
  //@XmlElementWrapper
  //@XmlElement(name="type")
  private List<AddressType> types = new LinkedList<AddressType>();

  public AddressComponent() {}
  
  
  public String getLongName() {
    return longName;
  }

  public AddressComponent setLongName(String longName) {
    this.longName = longName;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public AddressComponent setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public List<AddressType> getTypes() {
    return types;
  }
  /**/
  @Override
  public String toString() {
    String info = "[AddressComponent : longName="+longName+"; shortName="+shortName+"; types={";
    for (AddressType type : types) {
      info += type;
    }
    info += "}]";
    return info;
  }
  
}
