package com.simple.geocoding.repository;

import com.simple.geocoding.jpa.AddressComponentDao;
import com.simple.geocoding.jpa.AddressDao;
import com.simple.geocoding.jpa.AddressTypeDao;
import com.simple.geocoding.jpa.CoordsDao;
import com.simple.geocoding.jpa.GeometryDao;

public interface AddressRepository {

  AddressDao saveAddress(AddressDao dao);
  
  AddressDao saveAddressDaoWithoutComponentsAndTypes(AddressDao dao);
  
  AddressComponentDao saveAddressComponent(AddressComponentDao dao);
  
  AddressTypeDao saveAddressType(AddressTypeDao dao);
  
  CoordsDao saveCoords(CoordsDao dao);
  
  GeometryDao saveGeometryWithoutCoords(GeometryDao dao);
}
