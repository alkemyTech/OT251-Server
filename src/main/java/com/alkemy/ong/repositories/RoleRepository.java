package com.alkemy.ong.repositories;

import com.alkemy.ong.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Role findByName(String name);
}
