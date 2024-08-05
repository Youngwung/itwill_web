package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl {
	
	public PostQuerydslImpl() {
		// super();
		// 숨겨져있는 기본 생성자.
		// QuerydslRepositorySupport 클래스(super)는 기본생성자(super())가 없어서 에러가 뜸
		// QuerydslRepositorySupport 클래스의 생성자를 명시적으로 호출해야함.
		// QuerydslRepositorySupport 클래스는 아규먼트로 클래스를 받는다.
		// Entity 클래스를 넘겨주면 됨.
		super(Post.class);
	}

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id = {})", id);

		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // select p from Post p
		query.where(post.id.eq(id)); // query + where id = ?
		/* 
		 * QPost 객체 필드의 메서드 종류:
		 * eq: equals
		 * ne: not equals 
		 * gt: greater than
		 * lt: less than
		 * goe: Greater Than or Equal
		 * loe: Less Than or Equal
		 * isNull
		 * isNotNull
		 * contains : 문자열 포함 검사
		 * containsIgnoreCase : 대소문자 구분없이 문자열 포함 검사
		 * or, and: booleanExpression 끼리 연결해서 연산 실행
		 * not: booleanExpression을 부정
		 */
		Post entity = query.fetchOne(); // Post 타입으로 변환.
		return entity;
	}

	@Override
	public List<Post> searchByTitle(String keyword) {
		log.info("searchByTitleContainingIgnoreCase(keyword = {})", keyword );
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post); // select p from Post p
		query.where(post.title.containsIgnoreCase(keyword)); // contains와 upper 처리
		query.orderBy(post.id.desc()); // order by id desc;
		return query.fetch(); // 리스트로 변환
	}

	@Override
	public List<Post> searchByContent(String keyword) {
		log.info("searchByContent(keyword={})", keyword);

		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		query.where(post.content.containsIgnoreCase(keyword));
		query.orderBy(post.id.desc());
		List<Post> result = query.fetch();
		return result;
	}

	@Override
	public List<Post> searchByTitleAndContent(String keyword) {
		log.info("searchByTitleAndContent(keyword={})", keyword);
		QPost post = QPost.post;
		JPQLQuery<Post> query = 
			from(post)
			.where(post.title.containsIgnoreCase(keyword).or(post.content.containsIgnoreCase(keyword)))
			.orderBy(post.id.desc());
		List<Post> list = query.fetch();
		return list;
	}

	@Override
	public List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to) {
		log.info("searchByModifiedTime(from = {}, to = {})", from, to);
		QPost post = QPost.post;
		JPQLQuery<Post> query = 
			from(post).where(post.modifiedTime.between(from, to))
			.orderBy(post.id.desc());
		List<Post> list = query.fetch();
		return list;
	}

	@Override
	public List<Post> searchByAuthorAndtitle(String author, String keyword) {
		log.info("searchByAuthorAndtitle(author={}, keyword = {})",author, keyword );
		QPost post = QPost.post;
		JPQLQuery<Post> query = 
			from(post)
			.where(post.author.eq(author))
			.where(post.title.containsIgnoreCase(keyword))
			.orderBy(post.id.desc());
		List<Post> list = query.fetch();
		return list;
	}

	@Override
	public List<Post> searchByCategory(PostSearchRequestDto dto) {
		log.info("searchByCategory(dto={})", dto);
		String category = dto.getCategory();
		String keyword = dto.getKeyword();

		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);

		// BooleanBuilder: where() 메서드의 아규먼트인 BooleanExpression 객체를 생성할 수 있는객체.
		BooleanBuilder builder = new BooleanBuilder();
		switch (category) {
			case "t":
				builder.and(post.title.containsIgnoreCase(keyword));
			break;
			case "c":
				builder.and(post.content.containsIgnoreCase(keyword));
			break;
			case "tc":
				builder.and(post.title.containsIgnoreCase(keyword))
								.or(post.content.containsIgnoreCase(keyword));
			break;
			case "a":
				builder.and(post.author.containsIgnoreCase(keyword));
			break;
		}

		query.where(builder).orderBy(post.id.desc());
		List<Post> list = query.fetch();

		return list;
	}

	@Override
	public List<Post> searchByKeywords(String[] keywords) {
		log.info("searchByKeywords(keywords={})", keywords.toString());
		for (int i = 0; i < keywords.length; i++) {
			System.out.println(keywords[i]);
		}
		QPost post = QPost.post;
 		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for (int i = 0; i < keywords.length; i++) {
			builder.or(post.title.containsIgnoreCase(keywords[i])
							.or(post.content.containsIgnoreCase(keywords[i])));
		}
		List<Post> list = query.where(builder).orderBy(post.id.desc()).fetch();

		return list;
	}

	@Override
	public Page<Post> searchByKeywords(String[] keywords, Pageable pageable) {
		log.info("searchByKeywords(keywords = {}, pageable = {})",
						 Arrays.asList(keywords), pageable);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query = from(post);
		BooleanBuilder builder = new BooleanBuilder();
		for (String k : keywords) {
			builder.or(post.title.containsIgnoreCase(k)
					.or(post.content.containsIgnoreCase(k)));
		}
		query.where(builder);

		//Paging & Sorting 적용
		// 리턴을 하지 않아도 query가 페이징, 솔팅이 가능한 sql로 변경됨.
		getQuerydsl().applyPagination(pageable, query);

		// 한 페이지에 표시할 데이터를 fetch.
		// 리스트의 길이는 설정한 한 페이지에 보여줄 데이터 개수와 일치.
		List<Post> list = query.fetch();

		// 전체 레코드 개수를 fetch.
		// 모든 데이터의 개수를 리턴해줌.
		Long count = query.fetchCount();
		
		// Page<T> 객체를 생성.
		// Page는 인터페이스임. 인터페이스는 생성자가 없음.
		// Page의 객체를 생성하려면 Page를 구현한 구현클래스의 생성자를 호출해야됨.
		// 보통 구현클래스의 이름은 인터페이스+Impl로 만듬
		// PageImple의 생성자를 호출
		// 아규먼트로 (리스트, Pageable 객체, 전체 레코드 개수)를 넘김.
		Page<Post> page = new PageImpl<>(list, pageable, count);
		
		return page;
	}

	

}
