package com.alkemy.ong.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Slide;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface SlideRepository extends JpaRepository<Slide, UUID> {

	@Query("SELECT COALESCE(MAX(s.order),0) FROM Slide s")
	Integer getMaxOrder();

	boolean existsByOrder(Integer order);

	@Query("SELECT s FROM Slide s WHERE s.organizationId.id = :organizationId ORDER BY slide_order")
	List<Slide> getSlideByOrganization(UUID organizationId);
}
