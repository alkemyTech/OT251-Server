package com.alkemy.ong.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<NewsResponse> createNews(@RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(newsServices.createNews(newsRequest));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<NewsResponse> getNewsById(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.getById(id));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
		newsServices.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<NewsResponse> updateNews(@PathVariable UUID id, @RequestBody @Valid NewsRequest newsRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(newsServices.update(id, newsRequest));
	}


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
