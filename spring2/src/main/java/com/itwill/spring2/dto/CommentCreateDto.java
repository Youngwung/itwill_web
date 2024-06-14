package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {
  private Integer postId;
  private String ctext;
  private String username;

  // CommentCreteDto 타입을 Comment 타입으로 변환해서 리턴.
  public Comment toEntity() {
    return Comment.builder()
        .postId(postId)
        .ctext(ctext)
        .username(username)
        .build();
  }
}
