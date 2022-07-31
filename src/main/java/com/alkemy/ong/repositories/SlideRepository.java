package com.alkemy.ong.repositories;

import com.alkemy.ong.models.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide,UUID> {
}
