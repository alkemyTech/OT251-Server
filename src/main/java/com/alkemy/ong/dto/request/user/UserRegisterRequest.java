package com.alkemy.ong.dto.request.user;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRegisterRequest {

	@Schema(name = "id", description="User id", type="UUID", example="4c8d35a8-4c74-4404-9b42-f69b077b1354")
	private UUID id;

	@Schema(name = "firstName", required = true, description="User first name", type="String",example="Mery")
	@NotBlank(message = "The first Name field cannot be empty.")
	@Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
	private String firstName;

	@Schema(name = "lastName", required = true, description="User last name", type="String",example="Smith")
	@NotBlank(message = "The last Name field cannot be empty.")
	@Size(min = 2, max = 40, message = "The length of the last Name must be greater than 2 and less than 40 characters.")
	private String lastName;

	@Schema(name = "email", required = true, description="User email", type="String",example="MerySmith@mail.com")
	@Email(message = "Must be a properly formatted email address.")
	@NotEmpty(message = "The email field cannot be empty.")
	private String email;

	@Schema(name = "password", required = true, description="User email", type="String",example="D.saewe!")
	@Size(min = 6, message = "The password must have at least 6 characters.")
	@NotEmpty(message = "The password field cannot be empty.")
	private String password;

	@Schema(name = "photo", description="User photo in Base64", type="String",example="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAJUlEQVR42u3NQQEAAAQEsJNcdFLw2gqsMukcK4lEIpFIJBLJS7KG6yVo40DbTgAAAABJRU5ErkJggg==")
	private String photo;

}
