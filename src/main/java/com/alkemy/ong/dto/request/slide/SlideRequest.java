package com.alkemy.ong.dto.request.slide;

import javax.validation.constraints.NotBlank;

import com.alkemy.ong.models.Organization;

import lombok.Data;

@Data
public class SlideRequest {

	@NotBlank(message = "File encoded in base64 must be provided.")
	private String imageUrl;
	private String text;
	private Integer order;
	private Organization organizationId;

}
