package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CommentRepositoryTest {

	// CommentRepository의 CRUD 기능을 단위 테스트.
	
	@Autowired
	private CommentRepository coRepo;
	@Autowired
	private PostRepository postRepo;

	@Test // 성공
	public void testDependancyInjection() {
		log.info("testDependancyInjection");
		assertThat(coRepo).isNotNull();
		assertThat(postRepo).isNotNull();
	}

	// @Test //? 성공: findAll
	public void findAll() {
		log.info("findAll");
		List<Comment> list = coRepo.findAll();
		assertThat(list.size()).isEqualTo(0);
	}

	// @Test //? 성공 save(insert)
	public void insert() {
		log.info("insert");
		Comment comment = Comment.builder()
											.post(postRepo.findById(106L).orElseThrow())
											.ctext("내일 가평 놀러가지롱")
											.writer("빠지 처음가는 사람").build();
		log.info("comment={}", comment);
		coRepo.save(comment);
	}

	// @Test //? 성공: findById
	public void findById() {
		log.info("findById()");
		Comment comment = coRepo.findById(2L).orElseThrow();
		log.info("comment = {}", comment);
	}

	// @Test // ?성공: save(update)
	public void update() {
		log.info("update");
		Comment comment = coRepo.findById(2L).orElseThrow();
		log.info("comment = {}", comment);

		comment.update("내일 휴가임");
		log.info("update 호출 comment = {}", comment);

		comment = coRepo.save(comment);
		log.info("save 후 comment = {}", comment);
	}

	// @Test //? 성공: delete
	public void delete() {
		log.info("delete");
		coRepo.deleteById(2L);
	}
	
}
