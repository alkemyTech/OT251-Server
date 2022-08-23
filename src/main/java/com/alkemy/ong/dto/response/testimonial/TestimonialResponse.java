package com.alkemy.ong.dto.response.testimonial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class TestimonialResponse {


    @Schema(name = "id", description="Testimonial ID", type="UUID",example="4547c146-d9c7-4e65-bc6b-4f713d4v0edf")
    private UUID id;

    @Schema(name = "name", description="Testimonial name", type="String",example="name example")
    private String name;

    @Schema(name = "image", description="Testimonial image", type="String",example="imageTestimonial.jpg")
    private String image;

    @Schema(name = "content", description="Testimonial content", type="String",example="Testimonial content")
    private String content;

}
