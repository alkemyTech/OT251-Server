package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.slides.SlideRequest;
import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;

import java.util.UUID;

public interface ITestimonialService {

    public TestimonialResponse createTestimonial(TestimonialRequest testimoniaRequest);

    public TestimonialResponse update(UUID id, TestimonialRequest testimoniaRequest);

}
