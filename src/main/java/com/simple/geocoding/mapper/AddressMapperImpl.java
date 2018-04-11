package com.simple.geocoding.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.repository.AddressRepository;

@Service
public class AddressMapperImpl implements AddressMapper {

  @Autowired
  private AddressRepository repo;
  
  @Override
  public void saveAddress(Address address) {
    // TODO Auto-generated method stub
    
  }

}
