package com.userregistrationsystem.exception;

import com.userregistrationsystem.model.ErrorModel;
import com.userregistrationsystem.model.OutputModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class GeoHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorModel> handleGeoException(int value, String message) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(message);
//        errorModel.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(value)
                .body(errorModel);

    }


}
