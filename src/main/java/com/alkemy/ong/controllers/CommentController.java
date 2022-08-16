package com.alkemy.ong.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.services.ICommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private ICommentService commentService;
	
	@GetMapping()
	ResponseEntity<Page<CommentListResponse>> getAllComents(@PageableDefault(size = 10) Pageable pageable){
		return ResponseEntity.ok(commentService.getAllComents(pageable));
	}
	
}
