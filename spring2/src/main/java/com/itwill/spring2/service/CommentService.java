package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentDao commentDao; // 생성자에 의한 의존성 주입

  // 컨트롤러가 호출할 메서드 작성
  public List<CommentItemDto> readByPostId(Integer postId) {
    log.debug("readByPostId(postId={})", postId);

    // 리포지토리(영속성) 계층의 메서드를 호출해서 comments 테이블의 데이터를 검색.
    List<Comment> list = commentDao.selectByPostId(postId);

    // for문으로 리스트 타입 변환하기.
    // List<CommentItemDto> result = new ArrayList<>();
    // for (Comment c: list) {
    // CommentItemDto dto = CommentItemDto.fromEntity(c);
    // result.add(dto);
    // }
    // return result;
    return list.stream().map(CommentItemDto::fromEntity).toList();
  }

  public CommentItemDto readById(Integer id) {
    log.debug("readById({})", id);

    // 리포지토리 컴포넌트의 메서드를 호출해서 해당 아이디의 댓글 1개를 검색,
    Comment comment = commentDao.selectById(id);

    // Comment 타입을 CommentItemDto 탕비으로 변환해서 리턴.
    return CommentItemDto.fromEntity(comment);
  }

  public int create(CommentCreateDto dto) {
    log.debug("create({})", dto);

    // 리포지토리 계층의 메서드를 호출해서 comments 테이블에 insert.
    int result = commentDao.insert(dto.toEntity());

    return result;
  }

  public int update(CommentUpdateDto dto) {
    log.debug("update = {()}", dto);

    // 리포지토리 컴포넌트의 메서드를 호출해서 comments 테이블을 update
    int result = commentDao.update(dto.toEntity());

    return result;
  }

  public int deleteById(Integer id) {
    log.debug("deleteById({})", id);

    // 리포지토리 컴포넌트의 메서드를 호출해서 comments 테이블에서 댓글 1개 삭제
    int result = commentDao.deleteById(id);
    return result;
  }

  public int deleteByPostId(Integer postId) {
    log.debug("deleteByPostId({})", postId);
    int result = commentDao.deleteByPostId(postId);
    return result;
  }

}