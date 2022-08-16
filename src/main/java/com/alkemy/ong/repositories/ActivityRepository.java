package com.alkemy.ong.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Activity;

@Repository
public interface ActivityRepository extends  JpaRepository<Activity, UUID> {

    public Activity findByName(String name);

}
