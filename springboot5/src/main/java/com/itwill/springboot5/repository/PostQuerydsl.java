package com.itwill.springboot5.repository;

import com.itwill.springboot5.domain.Post;

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
	Post searchById(Long id);
}
