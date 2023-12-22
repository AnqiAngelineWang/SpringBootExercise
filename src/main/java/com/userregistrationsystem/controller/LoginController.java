package com.userregistrationsystem.controller;

//import com.userregistrationsystem.exception.GeoException;
import com.userregistrationsystem.exception.GeoHandler;
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
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OutputModel> controllerLogIn(@RequestBody RequestModel model)  { // throws GeoException  // HttpServletResponse response

        GeoHandler geoHandler = new GeoHandler();
            boolean nameNotEmpty = loginService.checkName(model.getUsername());
            if (!nameNotEmpty) {
                ResponseEntity<OutputModel> errorResponse = geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST,"Invalid username" );
                return errorResponse;

            }
            boolean passwordValid = loginService.checkPassword(model.getPassword());
            if (!passwordValid) {
                ResponseEntity<OutputModel> errorResponse = geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST,"Invalid password" );
                return errorResponse;

            }

            boolean ipValid = loginService.checkIP(model.getIpAddress());
            if (!ipValid) {
                ResponseEntity<OutputModel> errorResponse = geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST,"invalid IP Address" );
                return errorResponse;
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                response.setHeader("message", "invalid IP Address");
//                throw new GeoException(HttpStatus.BAD_REQUEST,"INVALID");
            }

            String inCanada = loginService.ipInCanada(model.getIpAddress());
            if(inCanada.equals("false")) {
                ResponseEntity<OutputModel> errorResponse = geoHandler.handleGeoException(HttpServletResponse.SC_BAD_REQUEST,"You are not in Canada" );
                return errorResponse;
            }



        //    if ((nameNotEmpty) && (passwordValid) && (inCanada) && (ipValid)) {
        OutputModel outputModel = new OutputModel();
        outputModel.setCity(inCanada);
        outputModel.setUuid(loginService.generateUUID());
        outputModel.setMessage("Welcome Page");
        return ResponseEntity.ok(outputModel);

    }

}











