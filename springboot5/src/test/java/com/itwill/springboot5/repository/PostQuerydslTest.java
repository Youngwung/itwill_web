package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PostQuerydslTest {

	@Autowired
	private PostRepository postRepo;
	
	// @Test
	public void testSearchById() {

		Post entity = postRepo.searchById(30L);

		log.info("entity = {}", entity);
		
	}

	@Test
	public void test() {
		List<Post> result = null;
		// result = postRepo.searchByTitle("1");
		// result = postRepo.searchByContent("2");
		// result = postRepo.searchByTitleAndContent("2");
		LocalDateTime from = LocalDateTime.of(2024, 7, 31, 0, 0);
		LocalDateTime to = LocalDateTime.of(2024, 8, 1, 0, 0, 0);
		// result = postRepo.searchByModifiedTime(from, to);
		// result = postRepo.searchByAuthorAndtitle("admin", "j");

		// PostSearchRequestDto dto = new PostSearchRequestDto();
		// dto.setCategory("tc");
		// dto.setKeyword("j");
		
		// result = postRepo.searchByCategory(dto);
		String[] keywords = "dum title".split(" ");
		result = postRepo.searchByKeywords(keywords);
		
		result.forEach((x) -> {System.out.println(x);});

		Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());

		Page<Post> page = postRepo.searchByKeywords(keywords, pageable);

		page.forEach((x) -> {System.out.println(x);});
	}
	
}