package com.userregistrationsystem.controller;

//import com.userregistrationsystem.exception.GeoException;

import com.userregistrationsystem.exception.GeoException;
import com.userregistrationsystem.exception.GeoHandler;
import com.userregistrationsystem.model.ErrorModel;
import com.userregistrationsystem.model.GeoLocResponse;
import com.userregistrationsystem.model.RequestModel;
import com.userregistrationsystem.model.OutputModel;
import com.userregistrationsystem.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    protected GeoHandler geoHandler;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ErrorModel> controllerLogIn(@RequestBody RequestModel model) throws GeoException {   // HttpServletResponse response


        boolean nameNotEmpty = loginService.checkName(model.getUsername());
        if (!nameNotEmpty) {
            return geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST, "Invalid username");

        }
        boolean passwordValid = loginService.checkPassword(model.getPassword());
        if (!passwordValid) {
            return geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST, "Invalid password");

        }

        boolean ipValid = loginService.checkIP(model.getIpAddress());
        if (!ipValid) {
            return geoHandler.handleGeoException(HttpServletResponse.SC_FORBIDDEN, "invalid IP Address");
        }

        String inCanada = loginService.ipInCanada(model.getIpAddress());
        if (inCanada.equals("false")) {
            return geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST, "You are not in Canada and not eligible to register.");
        }


        OutputModel outputModel = new OutputModel();
        outputModel.setCity(inCanada);
        outputModel.setUuid(loginService.generateUUID());
        outputModel.setMessage("Welcome Page");
        outputModel.setUserName(model.getUsername());
        return ResponseEntity.ok(outputModel);

    }

}











