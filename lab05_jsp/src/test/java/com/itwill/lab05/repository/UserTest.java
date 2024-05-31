package com.itwill.lab05.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
  private static final Logger log = LoggerFactory.getLogger(UserTest.class);

  private final UserDao userDao = UserDao.INSTANCE;

  @Test
  public void testSignIn() {
    // userid와 password가 모두 일치하는 경우:
    User test = User.builder().userid("admin").password("12345").build();// 현재 생성되어있는 계정 정보 입력.
    User user = userDao.selectByUserIdAndPassword(test);
    log.debug("test = {}", test);
    log.debug("user = {}", user);
    Assertions.assertNotNull(user);

    // userid와 password가 일치하지 않는 경우:
    User test2 = User.builder().userid("admin").password("1234").build();// 비밀번호를 틀리게 입력
    User user2 = userDao.selectByUserIdAndPassword(test2);
    Assertions.assertNull(user2);
    log.debug("test2 = {}", test2);
    log.debug("user2 = {}", user2);
  }

}
