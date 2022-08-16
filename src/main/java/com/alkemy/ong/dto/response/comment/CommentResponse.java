package com.alkemy.ong.dto.response.comment;

import java.sql.Timestamp;
import java.util.UUID;

import com.alkemy.ong.models.News;
import com.alkemy.ong.models.User;

import lombok.Data;

@Data
public class CommentResponse {

	private UUID id;
	private String body;
	private User userId;
	private News newsId;
	private Timestamp date;

}
