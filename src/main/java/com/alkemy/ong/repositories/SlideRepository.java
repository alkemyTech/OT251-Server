package com.alkemy.ong.repositories;

import com.alkemy.ong.models.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlideRepository extends JpaRepository<Slide,UUID> {
}
