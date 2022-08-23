package com.alkemy.ong.dto.request.news;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewsRequest {

    @Schema(name = "name", description="News name", type="String",example="New News... ", required = true)
    @NotBlank(message = "The name field cannot be empty")
	private String name;

    @Schema(name = "content", description="News content", type="String",example="News description...", required = true)
    @NotBlank(message = "The content field cannot be empty")
	private String content;

    @Schema(name = "category", description="News categoryID", type="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf", required = true)
    @NotNull(message = "The category field cannot be empty")
	private UUID categoryId;

    @Schema(name = "image", description="News image", type="String",example="newsImage.jpg")
    private String imageUrl;

}
