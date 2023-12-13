package com.userregistrationsystem.service;

import com.userregistrationsystem.model.GeoLocResponse;
import org.springframework.stereotype.Service;

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
    public GeoLocResponse checkIPLocation(String ipAddress) {

        if (ipAddress == null || ipAddress.isEmpty()) {
            System.out.println("IP address is empty.");
            return new GeoLocResponse();
        }
        if (!validIP(ipAddress)) {
            return new GeoLocResponse();
        } else {


            try {

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://ip-api.com/json/" + ipAddress))
                        .GET()
                        .build();

                //                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//                ResponseEntity<LoginResponse> response = client.send(request,   new JsonBodyHandler<>(LoginResponse.class)).body() ;

                GeoLocResponse response = HttpClient.newHttpClient()
                        .send(request, new JsonBodyHandler<>(GeoLocResponse.class)).body();

                return response;
            } catch (Exception e) {
                System.out.println("Can not find location.");
            }


        }
        return new GeoLocResponse();
    }

                // client.send(request,   new JsonBodyHandler<>(GeoLocResponse.class)).body() ;

//                Model model = HttpClient.newHttpClient()
//                        .send(request, new JsonBodyHandler<>(Model.class))
//                        .body();


//
//                HttpRequest request = HttpRequest.newBuilder(new URI("http://ip-api.com/json/" + ipAddress))
//                        .header("Accept", "application/json")
//                        .build();
//
//                GeoLocResponse response = HttpClient.newHttpClient()
//                        .send(request, new JsonBodyHandler<>(GeoLocResponse.class))
//                        .body();
//
//                System.out.println(response);




    @Override
    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID generated - " + uuid);
        System.out.println("UUID Version - " + uuid.version());
        return uuid.toString();
    }

    @Override
    public boolean validIP(String ip) {
      //TODO
        return true;
    }

    @Override
    public boolean ipInCanada(String countryName) {
        return countryName.equals("Canada");

    }

}
