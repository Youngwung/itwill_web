package com.itwill.springboot5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepo;

	public List<PostListItemDto> read() {
		log.info("read()");
		// 영속성(persistence/repository) 계층의 메서드를 호출해서 엔터티들의 리스트를 가져옴.
		List<Post> list = postRepo.findAllByOrderByIdDesc();
		log.info("list = {}", list);
		// List<Post>를 List<PostListItemDto> 타입으로 변환후 리턴.
		return list.stream().map((x) -> PostListItemDto.fromEntity(x)).toList();
	}

	public void create(PostCreateDto dto) {
		log.info("dto = {}", dto);
		Post entity = dto.toEntity();
		log.info("save 호출 전 entity = {}", entity);
		postRepo.save(entity);
		log.info("save 호출 후 entity = {}", entity);
	}
	
}
