package com.alkemy.ong.dto.response.testimonial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialPageResponse {

    private List<TestimonialResponse> testimonials;

    private String previous;

    private String next;

}