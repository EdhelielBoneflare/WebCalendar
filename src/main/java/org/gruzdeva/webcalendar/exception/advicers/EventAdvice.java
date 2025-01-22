package org.gruzdeva.webcalendar.exception.advicers;


import org.gruzdeva.webcalendar.presentation.dtos.CustomErrorMessage;
import org.gruzdeva.webcalendar.presentation.dtos.Message;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class EventAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongPath(NoHandlerFoundException e) {
        CustomErrorMessage response = new CustomErrorMessage("Endpoint not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorMessage> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation error.");
        CustomErrorMessage response = new CustomErrorMessage(errorMessage, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorMessage> handleConstraintValidation(ConstraintViolationException e) {
        CustomErrorMessage response = new CustomErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorMessage> handleNoBodyProvided(HttpMessageNotReadableException e) {
        CustomErrorMessage response = new CustomErrorMessage("Request body is missing.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongHttpParameterType(HttpMediaTypeNotAcceptableException e) {
        CustomErrorMessage response = new CustomErrorMessage("Invalid parameter type.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorMessage> handleValidationException(ValidationException e) {
        CustomErrorMessage response = new CustomErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<CustomErrorMessage> handleDateTimeException(DateTimeException e) {
        CustomErrorMessage response = new CustomErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Message> handleNoElement(NoSuchElementException e){
        Message response = new Message(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
