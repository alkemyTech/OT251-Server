package com.alkemy.ong.repositories;

import com.alkemy.ong.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	public User findByEmail(String email);

	public Boolean existsByEmail(String email);

	//rama 25 Brito Erika
	public UserDetails loadUserByEmail(String email);
}