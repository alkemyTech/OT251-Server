package com.alkemy.ong.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.request.comment.CommentRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.comment.CommentResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.CommentMapper;
import com.alkemy.ong.models.Comment;
import com.alkemy.ong.repositories.CommentRepository;
import com.alkemy.ong.repositories.NewsRepository;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private UserRepository userRepository;

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

	@Override
	public CommentResponse save(@Valid CommentRequest commentRequest) {
		if (!newsRepository.existsById(commentRequest.getNewId())) {
			throw new ResourceNotFoundException("News", "id", commentRequest.getNewId());
		}
		if (!userRepository.existsById(commentRequest.getUsertId())) {
			throw new ResourceNotFoundException("News", "id", commentRequest.getNewId());
		}

		return commentMapper.comments2CommentsResponse(
				commentRepository.save(commentMapper.commentRequest2Comments(commentRequest)));

	}
}
