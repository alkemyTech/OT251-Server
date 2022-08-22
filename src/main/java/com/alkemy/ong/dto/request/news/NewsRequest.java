package com.alkemy.ong.dto.request.news;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewsRequest {

    @ApiModelProperty(name = "name", position = 1, value="News name", dataType="String",example="New News... ", required = true)
    @NotBlank(message = "The name field cannot be empty")
	private String name;

    @ApiModelProperty(name = "content", position = 2, value="News content", dataType="String",example="News description...", required = true)
    @NotBlank(message = "The content field cannot be empty")
	private String content;

    @ApiModelProperty(name = "category", position = 4, value="News categoryID", dataType="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf", required = true)
    @NotNull(message = "The category field cannot be empty")
	private UUID categoryId;

    @ApiModelProperty(name = "image", position = 3, value="News image", dataType="String",example="newsImage.jpg")
    private String imageUrl;

}
