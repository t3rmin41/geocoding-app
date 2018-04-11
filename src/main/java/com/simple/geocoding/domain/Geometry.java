package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Geometry {

  //@XmlElement(name="location")
  private Coords location;
  
  //@XmlElement(name="location_type")
  private String locationType;
  
  //@XmlElementWrapper
  //@XmlElement(name="bounds")
  private List<Coords> bounds = new LinkedList<Coords>();
  
  //@XmlElementWrapper
  //@XmlElement(name="viewport")
  private List<Coords> viewport = new LinkedList<Coords>();

  public Geometry() {}
  
  
  public Coords getLocation() {
    return location;
  }
  public Geometry setLocation(Coords location) {
    this.location = location;
    return this;
  }
  public String getLocationType() {
    return locationType;
  }
  public Geometry setLocationType(String locationType) {
    this.locationType = locationType;
    return this;
  }
  public List<Coords> getBounds() {
    return bounds;
  }
  
  public List<Coords> getViewport() {
    return viewport;
  }
  /**/
  
  @Override
  public String toString() {
    String info = "[Geometry : locationType="+locationType+"; location="+location+"; bounds={";
            for (Coords coords : bounds) {
              info += coords;
            }
    info += "} viewport={";
            for (Coords coords : bounds) {
              info += coords;
            }
            info += "}]";
    return info;
  }
  
}
