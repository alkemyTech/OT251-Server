package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.services.ISlideService;

@RestController
@RequestMapping("/slides")
public class SlideController {

	@Autowired
	private ISlideService slideService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<SlideResponse>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(slideService.getAll());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<SlidesDetailsResponse> getById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(slideService.getById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSlides(@PathVariable UUID id) {
		slideService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
