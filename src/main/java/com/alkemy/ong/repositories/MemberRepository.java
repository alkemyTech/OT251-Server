package com.alkemy.ong.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,UUID> {
}
