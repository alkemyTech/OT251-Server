package com.alkemy.ong.dto.request.user;

import lombok.Data;

@Data
public class UserLoginRequest {

//	@ApiModelProperty(name = "email", required = true, position = 3, value="User email", dataType="String",example="MerySmith@mail.com")
	private String email;
	
//	@ApiModelProperty(name = "password", required = true, position = 4, value="User email", dataType="String",example="D.saewe!")
	private String password;
}
