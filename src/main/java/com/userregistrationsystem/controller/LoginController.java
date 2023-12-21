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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public OutputModel controllerLogIn(@RequestBody RequestModel model) {

        boolean nameNotEmpty = loginService.checkName(model.getUsername());
        boolean passwordValid = loginService.checkPassword(model.getPassword());
        boolean ipValid = loginService.checkIP(model.getIpAddress());
        boolean inCanada = loginService.ipInCanada(model.getIpAddress());

        if ((nameNotEmpty) && (passwordValid) && (inCanada) && (ipValid)) {
            OutputModel outputModel = new OutputModel();
            outputModel.setCity(loginService.city);
            outputModel.setUuid(loginService.generateUUID());
            outputModel.setMessage("Welcome Page");
            return outputModel;
        }
        return new OutputModel();
    }
}











