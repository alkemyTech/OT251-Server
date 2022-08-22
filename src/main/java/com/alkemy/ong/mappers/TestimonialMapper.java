package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.models.Testimonial;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestimonialMapper {


    public TestimonialResponse mapTestimonialResponse(Testimonial testimonial) {
        TestimonialResponse testimonialResponse = new TestimonialResponse();
        testimonialResponse.setId(testimonial.getId());
        testimonialResponse.setName(testimonial.getName());
        testimonialResponse.setImage(testimonial.getImage());
        testimonialResponse.setContent(testimonial.getContent());
        return testimonialResponse;
    }

    public Testimonial testimonialRequestToEntity (TestimonialRequest testimonialRequest) {
        if(testimonialRequest == null){return null;}

        Testimonial testimonial = new Testimonial();
        testimonial.setName(testimonialRequest.getName());
        testimonial.setImage(testimonialRequest.getImage());
        testimonial.setContent(testimonialRequest.getContent());

        return testimonial;
    }

    public List<TestimonialResponse> entities2ListResponse(List<Testimonial> testimonialEntities){
        return testimonialEntities.stream()
                .map(this::mapTestimonialResponse)
                .collect(Collectors.toList());
    }

}
