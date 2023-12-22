package com.userregistrationsystem.exception;

import com.userregistrationsystem.model.ErrorModel;
import com.userregistrationsystem.model.OutputModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GeoHandler {
//    @ExceptionHandler(GeoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<OutputModel> handleGeoException(int value, String message) {
        OutputModel errorModel = new OutputModel();
        errorModel.setMessage(message);
        errorModel.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(value)
                .body(errorModel);

    }


}
