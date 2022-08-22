package com.alkemy.ong.dto.response.news;

import java.util.UUID;

import com.alkemy.ong.models.Category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewsResponse {

	@ApiModelProperty(name = "id", position = 0, value="News ID", dataType="UUID",example="ea23b87b-207c-4261-90e2-1780a550c0c1")
	private UUID id;

	@ApiModelProperty(name = "name", position = 1, value="News name", dataType="String",example="New News... ")
	private String name;

	@ApiModelProperty(name = "content", position = 2, value="News content", dataType="String",example="News description...")
	private String content;

	@ApiModelProperty(name = "image", position = 3, value="News image", dataType="String",example="newsImage.jpg")
	private String image;

	@ApiModelProperty(name = "category", position = 4, value="News category", dataType="Category")
	private Category category;

	@ApiModelProperty(name = "type", position = 5, value="News type", dataType="String",example="news")
	private String type;
}
