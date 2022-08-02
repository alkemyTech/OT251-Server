package com.alkemy.ong.dto.request.user;

import lombok.Data;

@Data
public class UserLoginRequest {

	private String email;
	private String password;
}