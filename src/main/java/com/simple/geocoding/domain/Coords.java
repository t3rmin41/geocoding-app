package com.simple.geocoding.domain;

import javax.xml.bind.annotation.XmlElement;

public class Coords {

  private String type;
  
  //@XmlElement(name="lat")
  private Double latitude;
  
  //@XmlElement(name="lng")
  private Double longitude;

  public Coords() {}
  
  
  public String getType() {
    return type;
  }
  public Coords setType(String type) {
    this.type = type;
    return this;
  }
  public Double getLatitude() {
    return latitude;
  }
  public Coords setLatitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }
  public Double getLongitude() {
    return longitude;
  }
  public Coords setLongitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }
  /**/
  
  @Override
  public String toString() {
    return "[Coords : type="+type+"; latitude="+latitude+"; longitude="+longitude+"]";
  }
  
}
