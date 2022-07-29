package com.alkemy.ong.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alkemy.ong.dto.response.error.ErrorDetailsResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.NOT_FOUND);
	}

}
