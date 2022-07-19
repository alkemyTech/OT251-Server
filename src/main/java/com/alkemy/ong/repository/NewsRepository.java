package com.alkemy.ong.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.News;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
    
}
