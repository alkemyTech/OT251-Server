package com.alkemy.ong.dto.request.user;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.alkemy.ong.models.Role;

import lombok.Data;

@Data
public class UserUpdateRequest {

	private UUID id;

	@NotBlank(message = "The first Name field cannot be empty.")
	@Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
	private String firstName;

	@NotBlank(message = "The last Name field cannot be empty.")
	@Size(min = 2, max = 40, message = "The length of the last Name must be greater than 2 and less than 40 characters.")
	private String lastName;

	@Email(message = "Must be a properly formatted email address.")
	@NotEmpty(message = "The email field cannot be empty.")
	private String email;

	@Size(min = 6, message = "The password must have at least 6 characters.")
	@NotEmpty(message = "The password field cannot be empty.")
	private String password;

	private String photo;

	@NotBlank(message = "The roles field cannot be empty.")
	private Set<Role> roles;

}
