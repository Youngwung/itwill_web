package com.itwill.spring2.repository;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// MVC 아키텍쳐에서 Model 담당 클래스. 데이터베이스의 posts 테이블의 구조.
public class Post {
  private Integer id;
  private String title;
  private String content;
  private String author;
  private LocalDateTime createdTime; // 컬럼 이름에는 밑줄이 사용됐지만 필드 이름은 camel case를 사용한다.
  // 이건 mybatis-config.xml에서 자동으로 변환되도록 설정하고 mapper에서 이 모델 클래스를 등록하면 된다.

  private LocalDateTime modifiedTime;
  // mybatis-config.xml에서 typeAliases태그로 이 모델클래스의 패키지 경로를 변수로 정해두고
  // 매퍼 xml에서 계속 반복하게 될 패키지 경로를 간단하게 작성할 수 있다.
  // post-mapper.xml에서 select태그를 작성하고 전체검색 SQL 문장을 작성한다.
  // 인터페이스 PostDao를 만든다.
  // 인터페이스에서 post-mapper.xml에서 작성했던 속성 id와 같은 메서드를 구현함.
  //
  /*
   * 조건:
   * (1) post-mapper.xml의 mapper태그의 namespace속성의 위치와 이름이
   * 실제 인터페이스의 위치와 이름이 같아야 함.
   * (2) mybatis-config.xml의 typeAlias 태그의 type 속성이
   * 모델 클래스의 실제 위치와 이름이 맞아야 함.
   * (3) mybatis-config.xml의 typeAlias 태그의 alias 속성이
   * post-mapper.xml의 select태그의 resultType 속성과 같아야 함.
   * ===> 보통 sql문장의 결과물이 Post타입 일 거기 때문에 반복될 코드인
   * com.itwill.spring2.repository.Post를 변수에 저장해서 사용하는 거임.
   */

  // * 마지막으로 application-context.xml에 mybatis:scan태그를 추가하고
  // * "com.itwill.spring2.repository"값을 주면
  // ! base-package와 그 하위 패키지에 있는 인터페이스들을 구현한 객체를 생성해 줌.

  // * 그러면 인터페이스의 구현메서드가 호출될 때
  // * post-mapper.xml에 select 태그의 값인 sql문장을 실행해줌.
}
