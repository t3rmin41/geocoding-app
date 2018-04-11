package com.simple.geocoding.repository;

import com.simple.geocoding.jpa.AddressDao;

public interface AddressRepository {

  void saveAddressDao(AddressDao dao);
}
