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
public class GlobalHandleException extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> onIllegaArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ EmailAlreadyExistsException.class })
	public ResponseEntity<Object> handleResourceNotFound(EmailAlreadyExistsException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({ ForbiddenException.class })
	public ResponseEntity<Object> handleOngRequestException(ForbiddenException exception, WebRequest webRequest) {
		ErrorDetailsResponse ErrorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.FORBIDDEN);
	}
}
