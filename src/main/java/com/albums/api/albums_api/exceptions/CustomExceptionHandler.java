package com.albums.api.albums_api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private String INVALID_DATA = "INVALID_DATA";
    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleDataNotFound(DataNotFoundException ex, WebRequest wRequest){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(INVALID_DATA, details);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidRequest(DataNotFoundException ex, WebRequest wRequest){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}