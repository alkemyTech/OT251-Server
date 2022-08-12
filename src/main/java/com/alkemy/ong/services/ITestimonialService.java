package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;

public interface ITestimonialService {

    public TestimonialResponse createTestimonial(TestimonialRequest testimoniaRequest);

}
