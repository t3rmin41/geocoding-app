package com.simple.geocoding.domain;

import javax.xml.bind.annotation.XmlElement;

public class Coords {

  private String type;
  
  @XmlElement(name="lat")
  private Double latitude;
  
  @XmlElement(name="lng")
  private Double longitude;

  public Coords() {}
  
  /*
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
  /**/
  
}
