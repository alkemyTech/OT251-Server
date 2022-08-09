package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.news.NewsRequest;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.services.INewsServices;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private INewsServices newsServices;
        
        @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<NewsResponse> getNewsById(@PathVariable UUID id){
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getById(id));
	}
        
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("")
        public ResponseEntity<NewsResponse> createNews(@RequestBody(required = true) @Valid NewsRequest newsRequest, @RequestBody(required = true) MultipartFile image){
            return ResponseEntity.status(HttpStatus.CREATED).body(newsServices.createNews(newsRequest, image));
        }
}
