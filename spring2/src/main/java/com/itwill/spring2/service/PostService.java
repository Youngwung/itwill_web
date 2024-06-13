package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostSearchDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.repository.Post;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨테이너에 서비스 컴포넌트로 등록
@RequiredArgsConstructor // final 필드들을 초기화하는 아규먼트를 갖는 생성자.
public class PostService {

	// @Autowired
	// private PostDao postDao;
	// 애너테이션을 사용한 의존성 주입

	// 생성자에 의한 의존성 주입:
	// (!) final 필드 선언. (2) final 필드를 초기화하는 생성자 작성.
	private final PostDao postDao;

	// public PostService(PostDao postDao) {
	// this.postDao = postDao;
	// }

	// public List<Post> read() {
	// log.debug("read()");
	// return postDao.selectOrderByIdDesc();
	// }

	public List<PostListDto> read() {
		log.debug("read()");

		List<Post> list = postDao.selectOrderByIdDesc();

		// List<PostListDto> result = new ArrayList<>();
		// for (Post p : list) {
		// result.add(PostListDto.fromEntity(p));
		// } // 아래 스트림과 같은 for 문

		return list.stream().map(PostListDto::fromEntity) // map((x) -> PostListDto.fromEntity(x))
				// -> 여기서 x는 list의 원소 즉, Post 객체이다.
				// x를 하나씩 넘겨서 fromEntity(x)를 실행시키고
				.toList();
		// fromEntity의 리턴값(PostListDto객체)를 새로운 리스트에 추가한다.
	}

	public Post read(Integer id) {
		log.debug("read(id)");
		return postDao.selectById(id);
	}

	public int create(PostCreateDto dto) {
		log.debug("create({})", dto);

		int result = postDao.insertPost(dto.toEntity());
		log.debug("insert 결과 = {}", result);
		return result;
	}

	public int delete(int id) {
		log.debug("delete(id)");

		// 리포지토리 컴포넌트의 메서드를 호출해서 실제로 DB delete SQL문장을 실행한다.
		int result = postDao.deletePost(id);
		log.debug("delete 결과 = {}", result);
		return result;
	}

	public int update(PostUpdateDto dto) {
		log.debug("update({})", dto);
		int result = postDao.updatePost(dto.toEntity());
		log.debug("result = {}", result);
		return result;
	}

	public List<PostListDto> search(PostSearchDto dto) {
		List<Post> list = postDao.search(dto);
		return list.stream().map(PostListDto::fromEntity).toList();
	}

}
