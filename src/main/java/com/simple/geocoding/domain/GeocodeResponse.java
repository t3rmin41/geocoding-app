package com.simple.geocoding.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GeocodeResponse")
public class GeocodeResponse {

  @XmlElement(name="status")
  private String status;
  @XmlElement(name="result")
  private Address address;

  public GeocodeResponse() {}
  
  /*
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }
  /**/
  
}
