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
@Table(name = "ADDRESS_COMPONENT")
public class AddressComponentDao extends AddressParentDao {

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

}
