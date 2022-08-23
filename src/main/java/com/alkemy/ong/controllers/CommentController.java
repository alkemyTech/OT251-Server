package com.alkemy.ong.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.comment.CommentRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.comment.CommentResponse;
import com.alkemy.ong.services.ICommentService;

@RestController
@RequestMapping("/comments")
@Tag(name = "Comments", description = "Comments controller")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping()
	public ResponseEntity<Page<CommentListResponse>> getAllComents(@PageableDefault(size = 10) Pageable pageable) {
		return ResponseEntity.ok(commentService.getAllComents(pageable));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping()
	public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest commentRequest) {
		return ResponseEntity.ok(commentService.save(commentRequest));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CommentResponse> update(@PathVariable UUID id,
			@Valid @RequestBody CommentRequest commentRequest, HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok().body(commentService.update(id, commentRequest, httpServletRequest));
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id, @Valid @RequestBody CommentRequest commentRequest,
			HttpServletRequest httpServletRequest) {
		commentService.delete(id, commentRequest, httpServletRequest);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
