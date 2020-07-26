package com.guilherme.aequilibrium.transformers.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.guilherme.aequilibrium.transformers.exception.TeamNotFoundException;
import com.guilherme.aequilibrium.transformers.model.dto.ExceptionModelDTO;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionModelDTO> handleConnversion(RuntimeException ex) {
	return new ResponseEntity<>(new ExceptionModelDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
		HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionModelDTO> handleTeamNotFound(RuntimeException ex) {
	return new ResponseEntity<>(new ExceptionModelDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionModelDTO> handleEmptyResultDataAccess(RuntimeException ex) {
	return new ResponseEntity<>(new ExceptionModelDTO(HttpStatus.NOT_FOUND.value(), "Transformers not found"),
		HttpStatus.NOT_FOUND);
    }

    
    //TODO: improve messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionModelDTO> handleValidation(MethodArgumentNotValidException ex) {
	return new ResponseEntity<>(new ExceptionModelDTO(HttpStatus.BAD_REQUEST.value(),
		ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ExceptionModelDTO> handleValidation(HttpRequestMethodNotSupportedException ex) {
	return new ResponseEntity<>(new ExceptionModelDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()),
		HttpStatus.METHOD_NOT_ALLOWED);
    }

}