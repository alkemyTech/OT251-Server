package com.alkemy.ong.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

	List<Comment> findAllByOrderByTimestampsDesc();

}
