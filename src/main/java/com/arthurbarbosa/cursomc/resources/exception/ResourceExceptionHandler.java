package com.arthurbarbosa.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arthurbarbosa.cursomc.services.exceptions.DataIntegrityException;
import com.arthurbarbosa.cursomc.services.exceptions.ObjectNotFounException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFounException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFounException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> objectNotFound(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

}
