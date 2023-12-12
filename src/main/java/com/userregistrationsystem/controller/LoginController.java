package com.userregistrationsystem.controller;

import com.userregistrationsystem.model.GeoLocResponse;
import com.userregistrationsystem.model.RequestModel;
import com.userregistrationsystem.model.OutputModel;
import com.userregistrationsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //localhost:8080/login
//    @POSTMapping(value = "/login", method = RequestMethod.POST)
//    @PostMapping("/login")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public OutputModel controllerLogIn( @RequestBody RequestModel model) {
//    public OutputModel controllerLogIn(@RequestBody @RequestParam(name = "LoginRequest", required = true) RequestModel model) {
        // @Validated

        boolean nameNotEmpty = loginService.checkName(model.getUsername());
        boolean passwordValid = loginService.checkPassword(model.getPassword());
        GeoLocResponse ipInfo = loginService.checkIPLocation(model.getIpAddress());
        boolean inCanada = loginService.ipInCanada(ipInfo.getCountry());
        if ((nameNotEmpty) && (passwordValid) && (inCanada)) {


            OutputModel outputModel = new OutputModel();
            outputModel.setCity(ipInfo.getCity());
            outputModel.setUuid(loginService.generateUUID());
            outputModel.setMessage("Welcome Page");

            return outputModel;
        }
        return new OutputModel();
    }
}











