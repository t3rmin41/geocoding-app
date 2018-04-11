package com.simple.geocoding.domain;

public class AddressType {

  private String type;

  public String getType() {
    return type;
  }

  public AddressType setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  public String toString() {
    return "[AddressType : type="+type+"]";
  }
  
}
