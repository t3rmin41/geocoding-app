package com.simple.geocoding.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.simple.geocoding.jpa.AddressDao;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public void saveAddressDao(AddressDao dao) {
    em.merge(dao);
  }

}
