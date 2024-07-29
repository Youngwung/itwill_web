package com.itwill.springboot5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot5.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
