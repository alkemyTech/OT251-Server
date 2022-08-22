package com.alkemy.ong.dto.request.user;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegisterRequest {

//	@ApiModelProperty(name = "id", position = 0, value="User id",dataType="UUID", example="4c8d35a8-4c74-4404-9b42-f69b077b1354")
	private UUID id;

//	@ApiModelProperty(name = "firstName", required = true, position = 1, value="User first name", dataType="String",example="Mery")
	@NotBlank(message = "The first Name field cannot be empty.")
	@Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
	private String firstName;

//	@ApiModelProperty(name = "lastName", required = true, position = 2, value="User last name", dataType="String",example="Smith")
	@NotBlank(message = "The last Name field cannot be empty.")
	@Size(min = 2, max = 40, message = "The length of the last Name must be greater than 2 and less than 40 characters.")
	private String lastName;

//	@ApiModelProperty(name = "email", required = true, position = 3, value="User email", dataType="String",example="MerySmith@mail.com")
	@Email(message = "Must be a properly formatted email address.")
	@NotEmpty(message = "The email field cannot be empty.")
	private String email;

//	@ApiModelProperty(name = "password", required = true, position = 4, value="User email", dataType="String",example="D.saewe!")
	@Size(min = 6, message = "The password must have at least 6 characters.")
	@NotEmpty(message = "The password field cannot be empty.")
	private String password;

//	@ApiModelProperty(name = "photo", required = false, position = 5, value="User photo in Base64", dataType="String",example="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAJUlEQVR42u3NQQEAAAQEsJNcdFLw2gqsMukcK4lEIpFIJBLJS7KG6yVo40DbTgAAAABJRU5ErkJggg==")
	private String photo;

}
