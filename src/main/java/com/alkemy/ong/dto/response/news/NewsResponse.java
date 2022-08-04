package com.alkemy.ong.dto.response.news;

import java.util.UUID;

import com.alkemy.ong.models.Category;

import lombok.Data;

@Data
public class NewsResponse {
	private UUID id;
	private String name;
	private String content;
	private String image;
	private Category category;
}
