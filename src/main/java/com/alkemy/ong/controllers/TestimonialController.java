package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.services.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private ITestimonialService testimonialService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TestimonialResponse> createTestimonials(@RequestBody @Valid TestimonialRequest testimonialRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTestimonial(@PathVariable UUID id) {
        testimonialService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
