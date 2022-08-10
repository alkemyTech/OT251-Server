package com.alkemy.ong.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Slide;

@Repository
public interface SlideRepository extends JpaRepository<Slide, UUID> {

	@Query("SELECT COALESCE(MAX(s.order),0) FROM Slide s")
	Integer getMaxOrder();

	boolean existsByOrder(Integer order);
}
