package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.request.slides.SlideRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.services.ISlideService;

import javax.validation.Valid;

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
	@PostMapping("/public")
	public ResponseEntity<SlidesDetailsResponse> updateSlide(@RequestBody @Valid SlideRequest slideRequest){
		return ResponseEntity.status(HttpStatus.OK).body(slideService.update(slideRequest.getId(), slideRequest));
	}

}
