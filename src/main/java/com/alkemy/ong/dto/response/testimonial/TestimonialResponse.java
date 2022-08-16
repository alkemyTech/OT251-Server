package com.alkemy.ong.dto.response.testimonial;

import lombok.Data;

import java.util.UUID;

@Data
public class TestimonialResponse {

    private UUID id;

    private String name;

    private String image;

    private String content;

}
