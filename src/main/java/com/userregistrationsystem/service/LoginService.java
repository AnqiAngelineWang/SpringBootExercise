package com.userregistrationsystem.service;

import com.userregistrationsystem.model.GeoLocResponse;

public interface LoginService {

     boolean checkName(String theName) ;
     boolean checkPassword(String password) ;
    GeoLocResponse checkIPLocation(String url) ;
     String generateUUID() ;
      boolean validIP(String ip);
    boolean ipInCanada(String countryName);
}
