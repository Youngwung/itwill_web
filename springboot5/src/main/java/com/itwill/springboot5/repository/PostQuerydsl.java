package com.itwill.springboot5.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostSearchRequestDto;

/* 
 * Qeurydsl 사용:
 * 1. build.gradle 파일에 의존성과 빌드 옵션을 추가.
 * 2. ./gradelw clean build
 * 3. build/generated/querydsl 폴더에 com.itwill.springboot5.domain 패키지가 생성되고
 * 		패키지에는 @Entity 애너테이션이 있는 자바 클래스 파일 이름 앞에 "Q"가 붙은 Q클래스들이 자동 생성됨.
 * 4. 인터페이스(예: PostQuerydsl) 작성
 * 5. 인터페이스를 구현하는 클래스(PostQuerydslImpl)를 작성.
 * 6. (1) QuerydslRepositorySupport 상속(extends)
 * 			- 상위클래스가 기본 생성가를 갖고 있지 않기 때문에, 아규먼트를 갖는 super(args)를 명시적으로 호출.
 * 		(2) PostQuerydsl 인터페이스를 구현
 * 			- 추상 메서드의 body를 구현.
 * 7. JpaRepository를 상속받는 인터페이스(PostRepository)에서 PostQuerydsl 인터페이스를 상속.
 */
public interface PostQuerydsl {
	// id가 일치하는 엔터티 검색
	Post searchById(Long id);

	// title에 포함된 문자열 대소문자 구분없이 검색
	List<Post> searchByTitle(String keyword);

	// content에 포함된 문자열 대소문자 구분 없이 검색
	List<Post> searchByContent(String keyword);

	// title 또는 content에 포함된 문자열 대소문자 구분 없이 검색
	List<Post> searchByTitleAndContent(String keyword);

	// 수정시간 범위로 검색: where midified_time between ? and ?
	List<Post> searchByModifiedTime(LocalDateTime from, LocalDateTime to);

	// 작성자와 제목으로 검색. 작성자는 일치 조건, 제목은 대소문자 구분 없이 검색어를 포함
	List<Post> searchByAuthorAndtitle(String Author, String keyword);

	// 제목/내용/제목+내용/작성자 검색 => 동적 쿼리
	List<Post> searchByCategory(PostSearchRequestDto dto);

	// 제목 또는 내용에 검색어들 중 한 개라도 포함되어 있는 레코드들을 검색
	List<Post> searchByKeywords(String[] keywords);

	// Paging 처리
	Page<Post> searchByKeywords(String[] keywords, Pageable pageable);

}
