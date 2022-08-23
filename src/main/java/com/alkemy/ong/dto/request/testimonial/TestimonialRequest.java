package com.alkemy.ong.dto.request.testimonial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class TestimonialRequest {

    private UUID id;

    @Schema(name = "name", description="Testimonial name", type="String", example="name example", required = true)
    @NotBlank(message = "The name field cannot be empty.")
    @Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
    private String name;

    @Schema(name = "image", description="Testimonial image", type="String", example="imageTestimonial.jpg", required = true)
    @NotBlank(message = "The image field cannot be empty.")
    private String image;

    @Schema(name = "content", description="Testimonial content", type="String", example="Testimonial content")
    @NotEmpty(message = "The content field cannot be empty.")
    private String content;

}