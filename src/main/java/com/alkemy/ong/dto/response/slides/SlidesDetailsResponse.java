package com.alkemy.ong.dto.response.slides;

import java.util.UUID;

import com.alkemy.ong.models.Organization;

import lombok.Data;

@Data
public class SlidesDetailsResponse {

	private UUID id;
	private String imageUrl;
	private String text;
	private Integer order;
    private Organization organizationId;
}
