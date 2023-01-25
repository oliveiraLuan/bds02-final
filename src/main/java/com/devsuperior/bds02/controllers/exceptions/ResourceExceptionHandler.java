package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		StandardError standardError = new StandardError();
		standardError.setTimestamp(Instant.now());
		standardError.setError("Recurso não encontrado");
		standardError.setStatus(HttpStatus.NOT_FOUND.value());
		standardError.setPath(request.getRequestURI());
		standardError.setMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseIntegrityException(DatabaseException e, HttpServletRequest request){
		StandardError standardError = new StandardError();
		standardError.setTimestamp(Instant.now());
		standardError.setError("Database Integrity Violation");
		standardError.setMessage(e.getMessage());
		standardError.setPath(request.getRequestURI());
		standardError.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

}
