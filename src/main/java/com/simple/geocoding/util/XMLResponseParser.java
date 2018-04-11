package com.simple.geocoding.util;

import com.simple.geocoding.domain.Address;

public interface XMLResponseParser {

  Address parseAddressFromXMLResponse(String xml);
  
}
