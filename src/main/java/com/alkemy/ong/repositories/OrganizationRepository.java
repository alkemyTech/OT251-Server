package com.alkemy.ong.repositories;

import com.alkemy.ong.models.OrganizationModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel, UUID>{
    
}