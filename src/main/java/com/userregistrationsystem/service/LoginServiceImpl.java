package com.userregistrationsystem.service;

import com.userregistrationsystem.model.GeoLocResponse;
import com.userregistrationsystem.model.OutputModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("serviceImpl")
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean checkName(String theName) {

        if (theName == null || theName.isEmpty()) {
            System.out.println("Username is invalid");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String thePassword) {

        if (thePassword == null || thePassword.isEmpty()) {
            System.out.println("Password is empty");
            return false;
        }
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(thePassword);
        System.out.println(matcher.matches());
        return matcher.matches();

    }

    @Override
    public boolean checkIP(String ip) {
        if (ip == null || ip.isEmpty()) {
            System.out.println("IP address is empty.");
            return false;
        }

        String IP_PATTERN = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}";
        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    @Override
    public String ipInCanada(String ipAddress) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://ip-api.com/json/" + ipAddress))
                    .GET()
                    .build();

            System.out.println(request.uri());
            GeoLocResponse response = client.send(request, new JsonBodyHandler<>(GeoLocResponse.class)).body();

            if (response.getCountry().equals("Canada")) {
              String  city = response.getCity();
                return city;
            }
        } catch (Exception e) {

        }
        return "false";
    }

    @Override
    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(checkIP1("192.168.0.1"));
//    }

}
