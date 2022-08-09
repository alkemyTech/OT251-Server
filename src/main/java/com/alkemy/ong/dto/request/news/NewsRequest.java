package com.alkemy.ong.dto.request.news;

import com.alkemy.ong.models.Category;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class NewsRequest {

    private UUID id;

    @NotBlank(message = "The name field cannot be empty.")
    @Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
    private String name;

    private String content;

    @NotBlank(message = "The image field cannot be empty.")
    private String image;

    private Category category;

    private Timestamp updatedAt;

    private Boolean deleted;

}
