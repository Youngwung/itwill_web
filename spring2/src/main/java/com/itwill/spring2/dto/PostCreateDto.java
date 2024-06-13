package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.Data;

@Data
// 원래 Required ~~ 생성자만 들어있는데 final 필드가 없으므로 작동하지 않고
// 클래스가 자동으로 만들어주는 기본생성자가 생성된 상태이다.
public class PostCreateDto {
  // 필드 이름을 요청 파라미터 이름과 같게 선언 & 기본 생성자 & setter.
  private String title;
  private String content;
  private String author;

  public Post toEntity() {
    // 리포지토리의 메서드들은 Post를 사용하는 메서드들로 만들어져있다.
    // 리포지토리의 메서드에서 PostCreateDto를 사용하기 위해서는 Post타입으로 변환해야 한다.
    // 그걸 Dto 클래스에서 메서드로 작성하는 거임.
    // 이 메서드는 PostCreateDto 객체가 생성된 이후에 사용되므로 static일 필요가 없다.
    return Post.builder().title(title).content(content).author(author).build();
  }

}
