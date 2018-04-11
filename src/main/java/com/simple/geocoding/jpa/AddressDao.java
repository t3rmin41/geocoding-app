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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "PLACE_ID")
  private String placeId;
  @Column(name = "FORMATTED_ADDRESS")
  private String formattedAddress;
  @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="address")
  private GeometryDao geometry;
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="address")
  private List<AddressComponentDao> components = new LinkedList<AddressComponentDao>();
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="address")
  List<AddressTypeDao> types = new LinkedList<AddressTypeDao>();

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getPlaceId() {
    return placeId;
  }
  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }
  public String getFormattedAddress() {
    return formattedAddress;
  }
  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }
  public GeometryDao getGeometry() {
    return geometry;
  }
  public void setGeometry(GeometryDao geometry) {
    this.geometry = geometry;
  }
  public List<AddressComponentDao> getComponents() {
    return components;
  }
  public void setComponents(List<AddressComponentDao> components) {
    this.components = components;
  }
  public List<AddressTypeDao> getTypes() {
    return types;
  }
  public void setTypes(List<AddressTypeDao> types) {
    this.types = types;
  }

}
