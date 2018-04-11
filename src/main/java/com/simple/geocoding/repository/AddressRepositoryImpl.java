package com.simple.geocoding.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.simple.geocoding.jpa.AddressComponentDao;
import com.simple.geocoding.jpa.AddressDao;
import com.simple.geocoding.jpa.AddressTypeDao;
import com.simple.geocoding.jpa.CoordsDao;
import com.simple.geocoding.jpa.GeometryDao;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public AddressDao saveAddress(AddressDao dao) {
    return em.merge(dao);
  }
  
  @Override
  @Transactional
  public AddressDao saveAddressDaoWithoutComponentsAndTypes(AddressDao dao) {
    return em.merge(dao);
  }

  @Override
  @Transactional
  public AddressComponentDao saveAddressComponent(AddressComponentDao dao) {
    return em.merge(dao);
  }

  @Override
  @Transactional
  public AddressTypeDao saveAddressType(AddressTypeDao dao) {
    return em.merge(dao);
  }

  @Override
  @Transactional
  public CoordsDao saveCoords(CoordsDao dao) {
    return em.merge(dao);
  }

  @Override
  @Transactional
  public GeometryDao saveGeometryWithoutCoords(GeometryDao dao) {
    return em.merge(dao);
  }

}
