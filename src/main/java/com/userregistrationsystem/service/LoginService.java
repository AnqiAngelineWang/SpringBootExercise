package com.userregistrationsystem.service;

import com.userregistrationsystem.model.GeoLocResponse;

public interface LoginService {
    String city = "";

    boolean checkName(String theName);

    boolean checkPassword(String password);


    String generateUUID();

    String ipInCanada(String ipAddress);

    boolean checkIP(String ip);
}
