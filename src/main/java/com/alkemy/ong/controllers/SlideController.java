package com.alkemy.ong.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.response.slides.SlideResponse;
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
	

}
