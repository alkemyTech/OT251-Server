package com.alkemy.ong.dto.request.news;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsRequest {
        
        @NotBlank(message = "The name field cannot be empty")
	private String name;
        
        @NotBlank(message = "The content field cannot be empty")
	private String content;
        
        @NotBlank(message = "The category field cannot be empty")
	private UUID categoryId;
}
