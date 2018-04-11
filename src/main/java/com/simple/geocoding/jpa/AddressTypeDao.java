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
@Table(name = "ADDRESS_TYPE")
public class AddressTypeDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "TYPE")
  private String type;
  @Column(name = "PARENT_TYPE")
  private String parentType;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="PARENT_ID", nullable=false)
  private AddressParentDao parent;

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

  public AddressParentDao getParent() {
    return parent;
  }

  public void setParent(AddressParentDao parent) {
    this.parent = parent;
  }
  
}
