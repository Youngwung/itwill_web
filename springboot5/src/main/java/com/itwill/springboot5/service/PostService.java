package com.itwill.springboot5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepo;

	@Transactional(readOnly = true)
	public Page<PostListItemDto> read(int pageNo, Sort sort) {
		// pageNo와 sort를 아규먼트로 받음

		log.info("read(pageNo = {}, sort = {})", pageNo, sort);

		// Pageable 객체 생성
		Pageable pageable = PageRequest.of(pageNo, 10, sort);

		// 영속성(persistence/repository) 계층의 메서드를 호출해서 엔터티들의 리스트를 가져옴.
		// 생성한 Pageable 객체를 findAll 메서드에 아규먼트로 전달.
		// findAll(Pageable)의 리턴타입은 Page<T>
		Page<Post> list = postRepo.findAll(pageable);

		// Page<T> 타입이 가지고있는 메서드
		log.info("page.totalPages", list.getTotalPages()); // 전체 페이지 개수
		log.info("page.number = {}", list.getNumber()); // 현재 페이지 번호
		log.info("page.hasPrevious = {}", list.hasPrevious()); // 이전 페이지가 있는 지 여부
		log.info("page.hasNext", list.hasNext()); // 다음 페이지가 있는 지 여부

		// Page<Post>를 Page<PostListItemDto> 타입으로 변환후 리턴.
		Page<PostListItemDto> posts = list.map((x) -> PostListItemDto.fromEntity(x));
		return posts;
	}
	
	@Transactional
	public Long create(PostCreateDto dto) {

		log.info("dto = {}", dto);
		Post entity = postRepo.save(dto.toEntity());
		log.info("entity = {}", entity);

		return entity.getId(); // DB에 insert된 레코드의 PK를 리턴.
		// 이 프로젝트에는 작성 후 작성한 글의 상세보기 페이지로 이동할 수 있을 듯?
	}

	@Transactional(readOnly = true)
	public Post read(Long id) {
		log.info("read(id = {})", id);
		Post post = postRepo.findById(id).orElseThrow();
		log.info("post = {}", post);
		return post;
	}

	@Transactional
	public void delete(Long id){
		log.info("delete(id = {})", id);
		postRepo.deleteById(id);
	}

	@Transactional
	public void update(PostUpdateDto dto) {
		log.info("update(dto = {})", dto);

		// id로 Post 엔터티 객체를 찾음(DB select 쿼리 실행)
		Post entity = postRepo.findById(dto.getId()).orElseThrow();
		log.info("findById: {}", entity);

		// DB에서 검색한 엔터티 객체의 필드들을 업데이트(수정)
		// Post 엔터티는 안전성 문제로 setter를 만들지 않았으므로
		// 엔터티에서 메서드를 만들어야함. (쌤은 미리 만들었음.)
		entity = entity.update(dto.getTitle(), dto.getContent());
		log.info("entity: update(entity = {})", entity);

		// @Transactional 애너테이션을 사용한 경우,
		// DB에서 검색한 entity 객체가 변경되면 update 쿼리가 자동으로 실행.

		// @Transactional 애너테이션을 사용하지 않은 경우,
		// postRepo.save(entity) 직접 호출해야 함.

		// !주의: update 쿼리를 자동으로 실행하고 싶다면
		// ! 객체를 변경하기 전에 select 쿼리를 실행해야함
		// * 우리는 findById로 검색을 했음.
	}

	@Transactional(readOnly = true)
	public Page<Post> search(PostSearchRequestDto dto) {
		log.info("search(dto = {})",dto);
		Pageable pageable = PageRequest.of(dto.getP(), 10, Sort.by("id").descending());
		Page<Post> page = null;
		if(dto.getCategory().equals("t")) {
			page = postRepo.findByTitleContainingIgnoreCase(dto.getKeyword(), pageable);
		} else if(dto.getCategory().equals("c")) {
			page = postRepo.findByContentContainingIgnoreCase(dto.getKeyword(), pageable);
		} else if(dto.getCategory().equals("tc")) {
			page = postRepo.findByTitleOrContent(dto.getKeyword(), pageable);
		} else if (dto.getCategory().equals("a")) {
			page = postRepo.findByAuthorContainingIgnoreCase(dto.getKeyword(), pageable);
		}
		log.info("page = {}", page);
		return page;
	}
}
