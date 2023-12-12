package com.userRegistrationSystem.controller;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;
import io.ipgeolocation.api.exceptions.IPGeolocationError;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class SpringMVCController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-processform";
    }   // return for UI as: helloworld-showform.html

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld-showform";
    }

    // need a controller method to read form data and
    // add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        boolean flag = true;

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        if (theName == null || theName.isEmpty()) {
            model.addAttribute("messageName", "Username is invalid");
            flag = false;
        }

        String thePassword = request.getParameter("password");
        if (thePassword == null || thePassword.isEmpty()) {
            model.addAttribute("messagePassword", "Password is invalid");
            flag = false;
        }
        model.addAttribute("message", checkPassword(thePassword));
        if (!checkPassword(thePassword)) {
            flag = false;
        }

        String ipAddress = request.getParameter("ip");
        if (ipAddress == null || ipAddress.isEmpty()) {
            model.addAttribute("messageIP", "IP address is empty.");
            flag = false;
        }
        String city = checkLocation(ipAddress);
        if (city.equals("NO")) {
            model.addAttribute("messageCity", "You are not in Canada and can't register.");
            model.addAttribute("city", "unknown");
            flag = false;
        } else if (flag){
            model.addAttribute("city", city);
        }

        if (flag) {
            //TODO: re-direct to a new Welcome Page
            model.addAttribute("welcome", "Welcome Message: Welcome to the world. Your UUID is: ");
            model.addAttribute("uuid", generateUUID());
        }
        return "helloworld-showform";
    }


    private boolean checkPassword(String password) {
        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_#$%.]).{8,}$";

//        "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        System.out.println(matcher.matches());
        return matcher.matches();
    }

    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID generated - " + uuid);
        System.out.println("UUID Version - " + uuid.version());
        return uuid.toString();
    }


    private String checkLocation(String url) {
        JSONObject jsonObject = null;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://ip-api.com/json/" + url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(response.statusCode());
            jsonObject = new JSONObject(response.body());
            if (jsonObject.get("status").equals("success")) {
                System.out.println(jsonObject.get("query"));
                System.out.println(jsonObject.get("country"));
                return jsonObject.get("country").toString().equals("Canada") ? jsonObject.get("city").toString() : "NO";

            }
        } catch (Exception e) {
            System.out.println("Can not find location.");
        }
        //invalid IP address
        return "NO";

    }


    public static void main(String[] args) {
//        System.out.println(checkPassword("12asdfg6W$"));
//        System.out.println(generateUUID());
//       checkLocation("");


    }
}






    /*

^                                   # start of line
  (?=.*[0-9])                       # positive lookahead, digit [0-9]
  (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
  (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
  (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
  .                                 # matches anything
  {8,20}                            # length at least 8 characters and maximum of 20 characters
$                                   # end of line


     */