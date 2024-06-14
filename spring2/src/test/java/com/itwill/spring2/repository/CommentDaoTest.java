package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
// 서버 없이 xml을 읽을 수 있는 메인메서드같은 클래스를 지정해주는 애너테이션
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
// xml의 위치들을 SpringExtension클래스에게 알려주는 애너테이션.
)
public class CommentDaoTest {

  @Autowired // 스프링 컨테이너가 생성/관리하는 빈을 주입받음.
  private CommentDao commentDao;

  @Test
  public void testSelect() {
    Assertions.assertNotNull(commentDao); // 생성된 빈이 주입받았는 지 확인. 널이면 안됨.
    List<Comment> list = commentDao.selectByPostId(121);

    for (Comment c : list) {
      log.debug(c.toString());
    }
  }

  @Test
  public void testInsert() {
    Comment comment = Comment.builder()
        .postId(121)
        .username("admin")
        .ctext("댓글 입력 테스트")
        .build();
    int result = commentDao.insert(comment);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void update() {
    Comment comment = Comment.builder()
        .id(4)
        .ctext("업데이트 테스트")
        .build();
    int result = commentDao.update(comment);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void deleteById() {
    int result = commentDao.deleteById(4);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void deleteByPostId() {
    int result = commentDao.deleteByPostId(121);
    Assertions.assertEquals(2, result);
  }

  @Test
  public void selectCommentCount() {
    int count = commentDao.selectCommentCount(121);
    Assertions.assertEquals(3, count);
  }

  @Test
  public void selectById() {
    Comment comment = commentDao.selectById(6);
    Assertions.assertNotNull(comment);
    log.debug(comment.toString());
  }

}
