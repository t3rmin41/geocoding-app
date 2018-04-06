package com.simple.geocoding.test.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GeocodingAppTest extends TestCase {
  
  public GeocodingAppTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(GeocodingAppTest.class);
  }

  public void testApp() {
    assertTrue(true);
  }
}
