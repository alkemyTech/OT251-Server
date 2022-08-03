package com.alkemy.ong.dto.response.user;

import lombok.Data;

@Data
public class UserAuthenticatedResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String photo;
	
}
