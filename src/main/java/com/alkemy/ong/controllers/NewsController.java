package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.request.news.NewsRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.services.ICommentService;
import com.alkemy.ong.services.INewsServices;

@RestController
@RequestMapping("/news")
@Tag(name = "News", description = "News controller")
public class NewsController {

	@Autowired
	private INewsServices newsServices;

	@Autowired
	private ICommentService commentService;

	@Operation(summary = "Method to create a News", description = "Save a new News item in the database and return it")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "CREATED - News create successfully"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<NewsResponse> createNews(@RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(newsServices.createNews(newsRequest));
	}

	@Operation(summary = "Method to get a News by ID", description = "Return News details if the ID exist")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Return News details"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<NewsResponse> getNewsById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getById(id));
	}

	@Operation(summary = "Method to delete a News by ID", description = "Delete a News if the ID exist")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "NO CONTENT - News delete successfully"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
		newsServices.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "Method to update a News by ID", description = "Update a News if the ID exist")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - News update successfully"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<NewsResponse> updateNews(@PathVariable UUID id, @RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.update(id, newsRequest));
	}


	@Operation(summary = "Method to Get the comments for a News", description = "Get the comments for a News if the ID(News) exist")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Return comments"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - A News with that ID is Not Found")})
	@GetMapping("/{id}/comments")
	public ResponseEntity<Page<CommentListResponse>> listCommentsByNewsId(@PageableDefault(size = 10) Pageable pageable,
			@PathVariable(value = "id") UUID id) {
		return ResponseEntity.ok(commentService.getCommentsByNewsId(id, pageable));
	}

	@Operation(summary = "Method to get all news", description = "Get a page of news if they exist")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - Return a page of news"),
			@ApiResponse(responseCode = "404", description = "NOT FOUND - No news found")})
	@GetMapping
	public ResponseEntity<PageResultResponse<NewsResponse>> getAllNews(@RequestParam(defaultValue = "1") Integer page){
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getNewsList(page));
	}
}
