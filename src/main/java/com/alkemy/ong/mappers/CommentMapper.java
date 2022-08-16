package com.alkemy.ong.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.comment.CommentListResponse;
import com.alkemy.ong.models.Comment;

@Component
public class CommentMapper {

	public CommentListResponse comment2CommentListResponse(Comment comment) {
		CommentListResponse commentResponse = new CommentListResponse();
		commentResponse.setBody(comment.getBody());
		return commentResponse;
	}
	
	public List<CommentListResponse> comment2CommentList(List<Comment> commentsList) {
		return commentsList.stream().map(comments -> comment2CommentListResponse(comments)).collect(Collectors.toList());
	}

}
