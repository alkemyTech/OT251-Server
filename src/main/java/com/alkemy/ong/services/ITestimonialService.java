package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;

import java.util.UUID;

public interface ITestimonialService {

    public TestimonialResponse createTestimonial(TestimonialRequest testimoniaRequest);

    public void delete(UUID id);

}
