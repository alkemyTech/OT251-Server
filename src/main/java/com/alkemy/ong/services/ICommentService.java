package com.alkemy.ong.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alkemy.ong.dto.request.comment.CommentRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.comment.CommentResponse;

public interface ICommentService {


	Page<CommentListResponse> getAllComents(Pageable pageable);

	CommentResponse save(@Valid CommentRequest commentRequest);

}
