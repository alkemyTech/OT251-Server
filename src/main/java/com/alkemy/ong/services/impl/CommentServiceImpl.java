package com.alkemy.ong.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.ong.repositories.CommentRepository;
import com.alkemy.ong.services.ICommentService;

public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;
	
}
