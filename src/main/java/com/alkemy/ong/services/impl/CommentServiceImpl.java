package com.alkemy.ong.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.mappers.CommentMapper;
import com.alkemy.ong.models.Comment;
import com.alkemy.ong.repositories.CommentRepository;
import com.alkemy.ong.services.ICommentService;
import com.amazonaws.services.opsworkscm.model.ResourceNotFoundException;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public Page<CommentListResponse> getAllComents(Pageable pegeable) {
		List<Comment> listComments = commentRepository.findAllByOrderByTimestampsDesc();
		List<CommentListResponse> commentListResponse;

		if (!listComments.isEmpty()) {
			commentListResponse = commentMapper.comment2CommentList(listComments);
			final int start = (int) pegeable.getOffset();
			final int end = Math.min((start + pegeable.getPageSize()), commentListResponse.size());
			return new PageImpl<>(commentListResponse.subList(start, end), pegeable, commentListResponse.size());
		} else {
			throw new ResourceNotFoundException("Comments not found");
		}

	}
}
