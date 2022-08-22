package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.services.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.utils.ApiConstants.BOTH;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private ITestimonialService testimonialService;

    @GetMapping(path = "/get-all")
    @PreAuthorize(BOTH)
    public ResponseEntity<PageResultResponse<TestimonialResponse>> getTestimonials(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(testimonialService.getAllTestimonials(page));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TestimonialResponse> createTestimonials(@RequestBody @Valid TestimonialRequest testimonialRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialRequest));
    }

}