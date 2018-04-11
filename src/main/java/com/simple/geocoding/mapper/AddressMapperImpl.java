package com.simple.geocoding.mapper;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.geocoding.domain.Address;
import com.simple.geocoding.jpa.AddressComponentDao;
import com.simple.geocoding.jpa.AddressDao;
import com.simple.geocoding.jpa.AddressTypeDao;
import com.simple.geocoding.jpa.CoordsDao;
import com.simple.geocoding.jpa.GeometryDao;
import com.simple.geocoding.repository.AddressRepository;

@Service
public class AddressMapperImpl implements AddressMapper {

  @Autowired
  private AddressRepository repo;
  
  private AddressDao jpa = new AddressDao();
  
  private GeometryDao geometryDao = new GeometryDao();
  
  private List<AddressComponentDao> componentDaoList = new LinkedList<AddressComponentDao>();
  
  @Override
  public void saveAddress(Address address) {
    //AddressDao jpa = new AddressDao();
    
    jpa.setFormattedAddress(address.getFormattedAddress());
    jpa.setPlaceId(address.getPlaceId());
    jpa = repo.saveAddressDaoWithoutComponentsAndTypes(jpa);
    
    List<AddressTypeDao> types = new LinkedList<AddressTypeDao>();
    address.getTypes().stream().forEach(t -> {
      AddressTypeDao typeDao = new AddressTypeDao();
      typeDao.setType(t.getType());
      typeDao.setParentType("ADDRESS");
      typeDao.setParent(jpa);
      typeDao = repo.saveAddressType(typeDao);
      types.add(typeDao);
    });
    jpa.getTypes().addAll(types);
    List<AddressComponentDao> components = new LinkedList<AddressComponentDao>();
    address.getComponents().stream().forEach(c -> {
      AddressComponentDao componentDao = new AddressComponentDao();
      componentDao.setShortname(c.getShortName());
      componentDao.setLongname(c.getLongName());
      componentDao.setAddress(jpa);
      componentDao = repo.saveAddressComponent(componentDao);
      List<AddressTypeDao> componentTypes = new LinkedList<AddressTypeDao>();
      c.getTypes().stream().forEach(t -> {
        AddressTypeDao componentTypeDao = new AddressTypeDao();
        componentTypeDao.setType(t.getType());
        componentTypeDao.setParentType("ADDRESS_COMPONENT");
        //componentTypeDao.setParent(componentDao);
        //componentTypeDao = repo.saveAddressType(componentTypeDao);
        componentTypes.add(componentTypeDao);
      });
      //componentDao.setAddress(jpa);
      componentDao.getTypes().addAll(componentTypes);
      //componentDao = repo.saveAddressComponent(componentDao);
      componentDao.getTypes().stream().forEach(t -> {
        t.setParent(componentDao);
      });
      
      components.add(componentDao);
    });
    jpa.getComponents().addAll(components);
    
    //GeometryDao geometryDao = new GeometryDao();
    geometryDao.setLocationType(address.getGeometry().getLocationType());
    geometryDao = repo.saveGeometryWithoutCoords(geometryDao);
    
    CoordsDao location = new CoordsDao();
    location.setParentType("LOCATION");
    location.setType(address.getGeometry().getLocation().getType());
    location.setLatitude(address.getGeometry().getLocation().getLatitude());
    location.setLongitude(address.getGeometry().getLocation().getLongitude());
    location.setGeometry(geometryDao);
    location = repo.saveCoords(location);
    geometryDao.setLocation(location);
    List<CoordsDao> bounds = new LinkedList<CoordsDao>();
    address.getGeometry().getBounds().stream().forEach(c -> {
      CoordsDao coords = new CoordsDao();
      coords.setParentType("BOUNDS");
      coords.setType(c.getType());
      coords.setLatitude(c.getLatitude());
      coords.setLongitude(c.getLongitude());
      coords.setGeometry(geometryDao);
      coords = repo.saveCoords(coords);
      bounds.add(coords);
    });
    geometryDao.getBounds().addAll(bounds);
    
    List<CoordsDao> viewport = new LinkedList<CoordsDao>();
    address.getGeometry().getBounds().stream().forEach(c -> {
      CoordsDao coords = new CoordsDao();
      coords.setParentType("VIEWPORT");
      coords.setType(c.getType());
      coords.setLatitude(c.getLatitude());
      coords.setLongitude(c.getLongitude());
      coords.setGeometry(geometryDao);
      coords = repo.saveCoords(coords);
      viewport.add(coords);
    });
    geometryDao.getViewport().addAll(viewport);
    geometryDao.setAddress(jpa);
    
    jpa.setGeometry(geometryDao);
    repo.saveAddress(jpa);
  }

}
