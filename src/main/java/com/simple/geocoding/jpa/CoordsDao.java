package com.simple.geocoding.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COORDS")
public class CoordsDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "TYPE")
  private String type;
  @Column(name = "PARENT_TYPE")
  private String parentType;
  @Column(name = "LATITUDE")
  private Double latitude;
  @Column(name = "LONGITUDE")
  private Double longitude;
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="GEOMETRY_ID", nullable=false)
  private GeometryDao geometry;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getParentType() {
    return parentType;
  }
  public void setParentType(String parentType) {
    this.parentType = parentType;
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
  public GeometryDao getGeometry() {
    return geometry;
  }
  public void setGeometry(GeometryDao geometry) {
    this.geometry = geometry;
  }

}
