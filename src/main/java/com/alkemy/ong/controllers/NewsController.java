package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
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
public class NewsController {

	@Autowired
	private INewsServices newsServices;

	@Autowired
	private ICommentService commentService;

	@ApiOperation(value = "Method to create a News", notes = "Save a new News item in the database and return it")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED - News create successfully"),
			@ApiResponse(code = 400, message = "BAD REQUEST - Param invalid")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<NewsResponse> createNews(@RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(newsServices.createNews(newsRequest));
	}

	@ApiOperation(value = "Method to get a News by ID", notes = "Return News details if the ID exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Return News details"),
			@ApiResponse(code = 404, message = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<NewsResponse> getNewsById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getById(id));
	}

	@ApiOperation(value = "Method to delete a News by ID", notes = "Delete a News if the ID exist")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO CONTENT - News delete successfully"),
			@ApiResponse(code = 404, message = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
		newsServices.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ApiOperation(value = "Method to update a News by ID", notes = "Update a News if the ID exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - News update successfully"),
			@ApiResponse(code = 400, message = "BAD REQUEST - Param invalid"),
			@ApiResponse(code = 404, message = "NOT FOUND - A News with that ID is Not Found")})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<NewsResponse> updateNews(@PathVariable UUID id, @RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.update(id, newsRequest));
	}


	@ApiOperation(value = "Method to Get the comments for a News", notes = "Get the comments for a News if the ID(News) exist")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - Return comments"),
			@ApiResponse(code = 404, message = "NOT FOUND - A News with that ID is Not Found")})
	@GetMapping("/{id}/comments")
	public ResponseEntity<Page<CommentListResponse>> listCommentsByNewsId(@PageableDefault(size = 10) Pageable pageable,
			@PathVariable(value = "id") UUID id) {
		return ResponseEntity.ok(commentService.getCommentsByNewsId(id, pageable));
	}

	@GetMapping
	public ResponseEntity<PageResultResponse<NewsResponse>> getAllNews(@RequestParam(defaultValue = "1") Integer page){
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getNewsList(page));
	}
}
