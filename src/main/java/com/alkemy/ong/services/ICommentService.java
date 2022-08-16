package com.alkemy.ong.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alkemy.ong.dto.response.comment.CommentListResponse;

public interface ICommentService {


	Page<CommentListResponse> getAllComents(Pageable pageable);

}
