package com.itwill.springboot5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot5.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	@EntityGraph(attributePaths = "roles")
	public Optional<Member> findByUsername(String username);
	
}
