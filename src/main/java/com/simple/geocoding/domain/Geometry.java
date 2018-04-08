package com.simple.geocoding.domain;

import java.util.LinkedList;
import java.util.List;

public class Geometry {

  private List<Coords> bounds = new LinkedList<Coords>();
  private Coords location;
  private String locationType;
  private List<Coords> viewport = new LinkedList<Coords>();

  public List<Coords> getBounds() {
    return bounds;
  }
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
  public List<Coords> getViewport() {
    return viewport;
  }

}
