package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.slides.SlideCreateRequest;
import com.alkemy.ong.dto.request.slides.SlideRequest;
import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.services.ISlideService;

@RestController
@RequestMapping("/slides")
@Tag(name = "Slides", description = "Slides controller")
public class SlideController {

	@Autowired
	private ISlideService slideService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Void> createSlide(@RequestBody @Valid SlideCreateRequest SlideRequest) {
		slideService.create(SlideRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<SlideResponse>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(slideService.getAll());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<SlidesDetailsResponse> getById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(slideService.getById(id));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/public")
	public ResponseEntity<SlidesDetailsResponse> updateSlide(@RequestBody @Valid SlideRequest slideRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(slideService.update(slideRequest.getId(), slideRequest));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSlides(@PathVariable UUID id) {
		slideService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
