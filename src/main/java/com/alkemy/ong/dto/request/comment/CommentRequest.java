package com.alkemy.ong.dto.request.comment;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentRequest {

	@NotNull(message = "Id user can't be null.")
	private UUID usertId;

	@NotNull(message = "Id user can't be null.")
	private UUID newId;

	@NotBlank
	@NotNull(message = "The body can't be null.")
	@NotEmpty(message = "The body can't be empty.")
	private String body;

}
