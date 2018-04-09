package com.simple.geocoding.jms;

import java.io.Serializable;

public class QueueCityMessage implements Serializable {

  private String city;
  private String country;

  public String getCity() {
    return city;
  }
  public QueueCityMessage setCity(String city) {
    this.city = city;
    return this;
  }
  public String getCountry() {
    return country;
  }
  public QueueCityMessage setCountry(String country) {
    this.country = country;
    return this;
  }
  
}
