package com.alkemy.ong.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginRequest {

	@Schema(name = "email", required = true, description="User email", type="String",example="MerySmith@mail.com")
	private String email;
	
	@Schema(name = "password", required = true, description="User email", type="String",example="D.saewe!")
	private String password;
}
