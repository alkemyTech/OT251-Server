package com.alkemy.ong.dto.response.news;

import java.util.UUID;

import com.alkemy.ong.models.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewsResponse {

	@Schema(name = "id", description="News ID", type="UUID",example="ea23b87b-207c-4261-90e2-1780a550c0c1")
	private UUID id;

	@Schema(name = "name", description="News name", type="String",example="New News... ")
	private String name;

	@Schema(name = "content", description="News content", type="String",example="News description...")
	private String content;

	@Schema(name = "image", description="News image", type="String",example="newsImage.jpg")
	private String image;

	@Schema(name = "category", description="News category", type="Category")
	private Category category;

	@Schema(name = "type", description="News type", type="String",example="news")
	private String type;
}
