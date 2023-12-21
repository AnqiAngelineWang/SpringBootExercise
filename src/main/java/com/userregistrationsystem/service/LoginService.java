package com.userregistrationsystem.service;

import com.userregistrationsystem.model.GeoLocResponse;

public interface LoginService {

    boolean checkName(String theName);

    boolean checkPassword(String password);

    GeoLocResponse checkIPLocation1(String ipAddress);

    String generateUUID();

    boolean validIP(String ip);

    boolean ipInCanada(String countryName);
}
