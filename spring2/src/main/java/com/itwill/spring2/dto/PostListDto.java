package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO (Data Transfer Object)
// 뷰 <--> 컨트롤러, 컨트롤러 <--> 서비스 사이에서 데이터를 주고 받을 때 사용하는 객체.
@Data // @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
// 여기서 Required~~생성자는 final필드를 아규먼트로 갖는 생성자를 만들어주는 애너테이션인데
// fianl 필드가 존재하지 않으면 클래스가 기본으로 만들어주는 기본 생성자만 있는 상태가 된다.
// final 필드가 존재하면 생성자가 하나 생기므로 클래스가 기본으로 만들어주는 생성자가 없어지므로
// 기본생성자와 아규먼트를 갖는 생성자를 따로 만들어 주어야 한다.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListDto {

  private Integer id;
  private String title;
  private String author;
  private LocalDateTime modifiedTime;

  public static PostListDto fromEntity(Post post) {
    return PostListDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .author(post.getAuthor())
        .modifiedTime(post.getModifiedTime())
        .build();
  }

}
