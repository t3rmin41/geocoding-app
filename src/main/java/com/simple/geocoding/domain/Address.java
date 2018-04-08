package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;

public class Address {

  private String placeId;
  private String formattedAddress;
  
  private List<AddressComponent> components = new LinkedList<AddressComponent>();
  private List<AddressType> types = new LinkedList<AddressType>();
  
  public List<AddressComponent> getComponents() {
    return components;
  }

  public List<AddressType> getTypes() {
    return types;
  }

}
