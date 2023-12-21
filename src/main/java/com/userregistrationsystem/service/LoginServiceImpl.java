package com.userregistrationsystem.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.userregistrationsystem.model.GeoLocResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    //    @Override
//    public GeoLocResponse checkIPLocation(String ipAddress) {
//        return new GeoLocResponse();
//    }
    @Override
    public  GeoLocResponse checkIPLocation1(String ipAddress) {

        if (ipAddress == null || ipAddress.isEmpty()) {
            System.out.println("IP address is empty.");
            return new GeoLocResponse();
//        }
//        if (!validIP(ipAddress)) {
//            return new GeoLocResponse();
        } else {


            try {

//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create("http://ip-api.com/json/" + ipAddress))
//                        .GET()
//                        .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(new URI("http://ip-api.com/json/"))
                        .header("Accept", "application/json")
                        .build();
                System.out.println(request.uri());
                GeoLocResponse response = client.send(request, new JsonBodyHandler<>(GeoLocResponse.class)).body();
                System.out.println(response.getCity());
//                return response.body();
                System.out.println("a");
//                ObjectMapper objectMapper = new ObjectMapper();
//                Gson gson = new GsonBuilder.create();

                GeoLocResponse model = HttpClient.newHttpClient()
                        .send(request, new JsonBodyHandler<>(GeoLocResponse.class))
                        .body();

                System.out.println(model.getCity());


                // client.send(request,   new JsonBodyHandler<>(GeoLocResponse.class)).body() ;
                //                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                System.out.println("e");
//                GeoLocResponse response1 = HttpClient.newHttpClient()
//                        .send(request, new JsonBodyHandler<>(GeoLocResponse.class)).body();
//                System.out.println("e2");

                HttpClient client2 = HttpClient.newHttpClient();
                HttpResponse<String> response3 = client.send(request, HttpResponse.BodyHandlers.ofString());


                String json = response3.body();
                System.out.println(json);
                System.out.println("e3");

                HttpClient client1 = HttpClient.newHttpClient();
                HttpRequest request1 = HttpRequest.newBuilder()
                        .uri(URI.create("http://ip-api.com/json/" + ipAddress))
                        .build();

                HttpResponse<String> response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());

                System.out.println(response1.body());


                ObjectMapper objectMapper = new ObjectMapper();
                GeoLocResponse element = objectMapper.readValue(json, GeoLocResponse.class);
                System.out.println("e4");
                return element;
            } catch (Exception e) {
                System.out.println("Can not find location.");
            }


        }
        return new GeoLocResponse();
    }

//    public static void main(String[] args) {
//        LoginServiceImpl loginService = new LoginServiceImpl();
//        GeoLocResponse geoLocResponse = loginService.checkIPLocation1("45.44.245.7");
//
//        System.out.println(geoLocResponse.getCity());
//    }

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
