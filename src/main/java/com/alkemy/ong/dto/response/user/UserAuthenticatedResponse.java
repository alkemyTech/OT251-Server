package com.alkemy.ong.dto.response.user;

import lombok.Data;

@Data
public class UserAuthenticatedResponse {

//	@ApiModelProperty(name = "firstName", position = 0, value="User first name", dataType="String",example="Mery")
	private String firstName;
	
//	@ApiModelProperty(name = "lastName", position = 1, value="User last name", dataType="String",example="Smith")
	private String lastName;
	
//	@ApiModelProperty(name = "email", position = 2, value="User email", dataType="String",example="MerySmith@mail.com")
	private String email;
	
//	@ApiModelProperty(name = "photo", position = 3, value="User photo in Base64", dataType="String",example="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAJUlEQVR42u3NQQEAAAQEsJNcdFLw2gqsMukcK4lEIpFIJBLJS7KG6yVo40DbTgAAAABJRU5ErkJggg==")
	private String photo;
	
}
