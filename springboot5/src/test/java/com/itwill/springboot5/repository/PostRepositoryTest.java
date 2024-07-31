package com.itwill.springboot5.repository;



import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PostRepositoryTest {
	// PostRepository 의존성 주입
	@Autowired // 테스트 클래스는 생성자에 의한 의존성 주입이 불가능
	// 기본 생성자만 가지고 있기 때문. 그래서 RequiredContructor 애너테이션으로 초기화할 수 없음.
	private PostRepository postRepo;
	
	// @Test
	public void testDependancyInjection() {
		assertThat(postRepo).isNotNull(); // poseRepo 객체가 null이 아니면 테스트 성공.
		log.info("postRepo = {}", postRepo);
	}

	// @Test
	public void testFindAll() {
		List<Post> list = postRepo.findAll();
		// assertThat(list.size()).isEqualTo(0);
		list.forEach((x) -> {System.out.println(x);});
	}

	// @Test
	public void testSave() {
		Post entity = Post.builder().title("JAP 저장 테스트")
										.content("Spring Boot + JPA 저장 테스트")
										.author("admin")
										.build();
		log.info("save 전: {}", entity);										
		// save(): insert 쿼리 실행
		postRepo.save(entity);
		log.info("save 후: {}", entity);	
		// update 쿼리
		// Post post = postRepo.save(entity);
	}	

	// @Test
	public void testUpdate() {
		// PK(id)로 엔터티를 검색:
		Post entity = postRepo.findById(1L).orElseThrow();
		log.info("findById 결과 = {}", entity);

		entity = entity.update("update 테스트", "JPA update 테스트");
		log.info("update 호출 = {}", entity);

		// update 쿼리 실행: 
		// @Id 필드가 null이 아닌 경우(레코드가 있는 경우)
		// 엔터티 객체가 DB 에 있는 레코드와 다른 경우.
		// 두 조건 모두 만족해야 update 쿼리를 실행.
		postRepo.save(entity);
		log.info("save 호출 후 = {}", entity);
	}

	// @Test
	public void testDelete() {
		postRepo.deleteById(1L); // 리턴 타입 void임.
	}

	// @Test
	public void makeDummyData() {
		List<Post> data = new ArrayList<>();
		for(int i = 1; i <= 50; i++) {
			Post post = Post.builder()
										.title("Dummy Title #"+i)
										.content("dummy content #" + i)
										.author("admin")
										.build();
			data.add(post);
		}
		postRepo.saveAll(data);
		// 아규먼트 타입 => Iterable(반복 가능한 타입)
	}
	
}
