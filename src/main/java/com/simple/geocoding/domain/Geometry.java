package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Geometry {

  @XmlElement(name="location")
  private Coords location;
  
  @XmlElement(name="location_type")
  private String locationType;
  
  @XmlElementWrapper
  @XmlElement(name="bounds")
  private List<Coords> bounds = new LinkedList<Coords>();
  
  @XmlElementWrapper
  @XmlElement(name="viewport")
  private List<Coords> viewport = new LinkedList<Coords>();

  public Geometry() {}
  
  /*
  public Coords getLocation() {
    return location;
  }
  public void setLocation(Coords location) {
    this.location = location;
  }
  public String getLocationType() {
    return locationType;
  }
  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }
  public List<Coords> getBounds() {
    return bounds;
  }
  
  public List<Coords> getViewport() {
    return viewport;
  }
  /**/
  
}
