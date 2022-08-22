package com.alkemy.ong.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.config.security.jwt.utils.JwtTokenProvider;
import com.alkemy.ong.dto.request.comment.CommentRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.comment.CommentResponse;
import com.alkemy.ong.exception.ForbiddenException;
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
	private JwtTokenProvider jwtTokenProvider;

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

	@Override
	public CommentResponse update(UUID id, @Valid CommentRequest commentRequest,
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String email = jwtTokenProvider.getUsernameJWT(token);
		List<String> roles = jwtTokenProvider.extractRoles(token);

		if (validUser(email, id) || isAdmin(roles)) {
			Comment comment = commentMapper.commentRequest2Comments(commentRequest);
			comment = commentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
			comment.setBody(comment.getBody());
			commentRepository.save(comment);
			return commentMapper.comments2CommentsResponse(comment);
		} else {
			throw new ForbiddenException(
					"You do not have permission to modify this comment you need to be the owner or administrator");
		}

	}

	@Override
	public void delete(UUID id, @Valid CommentRequest commentRequest, HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String email = jwtTokenProvider.getUsernameJWT(token);
		List<String> roles = jwtTokenProvider.extractRoles(token);

		if (validUser(email, id) || isAdmin(roles)) {
			Comment comment = commentMapper.commentRequest2Comments(commentRequest);
			comment = commentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
			commentRepository.delete(comment);
		} else {
			throw new ForbiddenException(
					"You do not have permission to delete this comment you need to be the owner or administrator");
		}
	}

	@Override
	public Page<CommentListResponse> getCommentsByNewsId(UUID id, Pageable pageable) {
		List<Comment> commentsList = commentRepository.findCommentsByNewsId(id);
		if (commentsList.isEmpty()) {
			throw new ResourceNotFoundException("News", "id", id);
		} else {
			List<CommentListResponse> commentListResponse;
			commentListResponse = commentMapper.comment2CommentList(commentsList);
			final int start = (int) pageable.getOffset();
			final int end = Math.min((start + pageable.getPageSize()), commentListResponse.size());
			return new PageImpl<>(commentListResponse.subList(start, end), pageable, commentListResponse.size());
		}
	}

	public Boolean validUser(String email, UUID commentId) {
		Optional<String> owner = commentRepository.findOwnerEmail(commentId);
		return owner.map(s -> s.equals(email)).orElse(false);
	}

	private boolean isAdmin(List<String> roles) {
		return roles.contains("ADMIN");
	}

}
