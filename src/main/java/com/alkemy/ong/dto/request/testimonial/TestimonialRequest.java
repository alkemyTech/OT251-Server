package com.alkemy.ong.dto.request.testimonial;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class TestimonialRequest {

    private UUID id;

    @NotBlank(message = "The name field cannot be empty.")
    @Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
    private String name;

    @NotBlank(message = "The image field cannot be empty.")
    private String image;

    @NotEmpty(message = "The content field cannot be empty.")
    private String content;

}