package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.services.ITestimonialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.alkemy.ong.utils.ApiConstants.BOTH;

@RestController
@RequestMapping("/testimonials")
@Tag(name = "Testimonials", description = "Testimonials controller")
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

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTestimonial(@PathVariable UUID id) {
		testimonialService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/public")
	public ResponseEntity<TestimonialResponse> updateTestimonial(
			@RequestBody @Valid TestimonialRequest testimonialRequest) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(testimonialService.update(testimonialRequest.getId(), testimonialRequest));
	}

}