package com.alkemy.ong.repositories;

import com.alkemy.ong.models.Testimonial;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, UUID>{
    
}
