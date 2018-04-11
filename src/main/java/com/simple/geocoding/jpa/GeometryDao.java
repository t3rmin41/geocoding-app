package com.simple.geocoding.jpa;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GEOMETRY")
public class GeometryDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "LOCATION_TYPE")
  private String locationType;
  @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="geometry")
  private CoordsDao location;
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="geometry")
  private List<CoordsDao> bounds = new LinkedList<CoordsDao>();
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="geometry")
  private List<CoordsDao> viewport = new LinkedList<CoordsDao>();
  @OneToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="ADDRESS_ID", nullable=false)
  private AddressDao address;
  
  public String getLocationType() {
    return locationType;
  }
  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }
  public CoordsDao getLocation() {
    return location;
  }
  public void setLocation(CoordsDao location) {
    this.location = location;
  }
  public List<CoordsDao> getBounds() {
    return bounds;
  }
  public void setBounds(List<CoordsDao> bounds) {
    this.bounds = bounds;
  }
  public List<CoordsDao> getViewport() {
    return viewport;
  }
  public void setViewport(List<CoordsDao> viewport) {
    this.viewport = viewport;
  }
  
}
