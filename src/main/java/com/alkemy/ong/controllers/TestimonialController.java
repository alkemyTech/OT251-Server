package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.services.ITestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

	@Operation(summary = "Method to get all Testimonials", description = "Return all Testimonials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Return all Testimonials"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - No Testimonials Found")})
    @GetMapping(path = "/get-all")
    @PreAuthorize(BOTH)
    public ResponseEntity<PageResultResponse<TestimonialResponse>> getTestimonials(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(testimonialService.getAllTestimonials(page));
    }

	@Operation(summary = "Method to create a Testimonial", description = "Save a new Testimonial item in the database and return it")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "CREATED - Testimonial create successfully"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TestimonialResponse> createTestimonials(@RequestBody @Valid TestimonialRequest testimonialRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialRequest));
    }

	@Operation(summary = "Method to delete a Testimonial by ID", description = "Delete a Testimonial if the ID exist")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "NO CONTENT - Testimonial delete successfully"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A Testimonial with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTestimonial(@PathVariable UUID id) {
		testimonialService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "Method to update a Testimonial by ID", description = "Update a Testimonial if the ID exist")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Testimonial update successfully"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A Testimonial with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<TestimonialResponse> updateTestimonial(@PathVariable UUID id,
			@RequestBody @Valid TestimonialRequest testimonialRequest) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(testimonialService.update(id, testimonialRequest));
	}

}