package com.alkemy.ong.dto.response.error;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetailsResponse {

	private Date timestamp;
	private String message;
	private String details;

	
	public ErrorDetailsResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
}
