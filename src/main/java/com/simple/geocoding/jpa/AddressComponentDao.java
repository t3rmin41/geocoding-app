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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_COMPONENT")
public class AddressComponentDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "SHORT_NAME")
  private String shortname;
  @Column(name = "LONG_NAME")
  private String longname;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="ADDRESS_ID", nullable=false)
  private AddressDao address;
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="component")
  List<AddressComponentTypeDao> types = new LinkedList<AddressComponentTypeDao>();

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getShortname() {
    return shortname;
  }
  public void setShortname(String shortname) {
    this.shortname = shortname;
  }
  public String getLongname() {
    return longname;
  }
  public void setLongname(String longname) {
    this.longname = longname;
  }
  public AddressDao getAddress() {
    return address;
  }
  public void setAddress(AddressDao address) {
    this.address = address;
  }
  public List<AddressComponentTypeDao> getTypes() {
    return types;
  }
  public void setTypes(List<AddressComponentTypeDao> types) {
    this.types = types;
  }


}
