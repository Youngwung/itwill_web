package com.itwill.springboot5.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
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

}
