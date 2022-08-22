package com.alkemy.ong.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.request.comment.CommentRequest;
import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.dto.response.comment.CommentResponse;
import com.alkemy.ong.models.Comment;
import com.alkemy.ong.repositories.NewsRepository;
import com.alkemy.ong.repositories.UserRepository;

@Component
public class CommentMapper {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private UserRepository userRepository;
	
	public CommentListResponse comment2CommentListResponse(Comment comment) {
		CommentListResponse commentResponse = new CommentListResponse();
		commentResponse.setBody(comment.getBody());
		return commentResponse;
	}

	public List<CommentListResponse> comment2CommentList(List<Comment> commentsList) {
		return commentsList.stream().map(comments -> comment2CommentListResponse(comments))
				.collect(Collectors.toList());
	}

	public CommentResponse comments2CommentsResponse(Comment comment) {
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setId(comment.getId());
		commentResponse.setBody(comment.getBody());
		commentResponse.setNewsId(comment.getNewsId());
		commentResponse.setUserId(comment.getUserId());
		commentResponse.setDate(comment.getTimestamps());
		return commentResponse;
	}
	
	public Comment commentRequest2Comments(CommentRequest commentRequest) {
		Comment comment = new Comment();
		comment.setBody(commentRequest.getBody());
		comment.setNewsId(newsRepository.findById(commentRequest.getNewId()).get());
		comment.setUserId(userRepository.findById(commentRequest.getNewId()).get());
		return comment;
	}
	


}
