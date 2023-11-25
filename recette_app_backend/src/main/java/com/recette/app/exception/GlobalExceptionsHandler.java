package com.recette.app.exception;


import com.recette.app.exception.errors.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> notFoundException(CustomException ex){

        ErrorResponse response = new ErrorResponse(
                new Date(),
                404,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}