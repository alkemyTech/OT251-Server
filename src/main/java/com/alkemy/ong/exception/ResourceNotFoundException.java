package com.alkemy.ong.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private UUID fieldValue;
	private String fieldValueString;

	public ResourceNotFoundException(String resourceName, String fieldName, UUID fieldValue) {
		super(String.format("%s not found : %s : '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString) {
		super(String.format("%s not found : %s : '%s'", resourceName, fieldName, fieldValueString));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueString = fieldValueString;
	}
	
	public ResourceNotFoundException(String resourceName) {
		super(String.format("%s not found", resourceName));
	}

	public ResourceNotFoundException() {
		super(String.format("Resource not found"));
	}
}
