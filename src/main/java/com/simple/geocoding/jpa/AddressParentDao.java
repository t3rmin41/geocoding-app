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
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "ADDRESS_PARENT")
@Inheritance(strategy=InheritanceType.JOINED)
public class AddressParentDao {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="parent")
  private List<AddressTypeDao> types = new LinkedList<AddressTypeDao>();

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public List<AddressTypeDao> getTypes() {
    return types;
  }
  public void setTypes(List<AddressTypeDao> types) {
    this.types = types;
  }

}
