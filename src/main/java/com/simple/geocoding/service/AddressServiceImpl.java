package com.simple.geocoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private AddressMapper mapper;
  
  @Override
  public void saveAddress(Address address) {
    mapper.saveAddress(address);
  }

}
