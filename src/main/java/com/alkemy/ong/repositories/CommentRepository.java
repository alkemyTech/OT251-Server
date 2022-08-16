package com.alkemy.ong.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

	List<Comment> findAllByOrderByTimestampsDesc();

	 @Query(value = "select u.email " +
	            "from comments c join users u on u.id = c.user_id " +
	            "where c.comments_id = ?1", nativeQuery = true)
	Optional<String> findOwnerEmail(UUID commentId);

	List<Comment> findCommentsByNewsId(UUID id);

}
