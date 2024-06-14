package com.itwill.spring2.repository;

import java.util.List;

// 매퍼 xml 파일과 연결.
public interface CommentDao {

  // 포스트에 달려 있는 모든 댓글을 검색.
  // 추상 메서드의 아규먼트는 SQL 문장의 ? 부분이다.
  List<Comment> selectByPostId(Integer postId);

  // 포스트에 새로운 댓글 추가
  int insert(Comment comment);

  // 댓글 내용, 수정 시간을 업데이트
  int update(Comment comment);

  // 댓글 아이디로 삭제
  int deleteById(Integer id);

  // 포스트에 달려 있는 모든 댓글 삭제
  int deleteByPostId(Integer postId);

  // 포스트에 달려 있는 댓글 개수를 검색
  int selectCommentCount(Integer postId);

  // 댓글 아이디(PK)로 검색하기.
  Comment selectById(Integer id);

}
