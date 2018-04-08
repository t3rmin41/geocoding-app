package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;

public class AddressComponent {

  private String longName;
  private String shortName;
  private List<AddressType> types = new LinkedList<AddressType>();

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

}
