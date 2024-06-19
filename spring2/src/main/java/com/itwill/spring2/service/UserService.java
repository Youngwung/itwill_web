package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.dto.UserSignInDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDao userDao;

  // 아이디 중복 체크: true - 중복되지않은 사용 가능한 아이디, false - 중복된 아이디
  public boolean checkUserid(String userid) {
    log.debug("checkUserid({})", userid);
    User user = userDao.selectByUserid(userid);

    if (user == null) { // 유저 아이디가 중복되지 않은 경우
      return true; // 회원가입 가능
    } else { // 유저 아이디가 중복 된 경우
      return false; // 회원가입 불가능
    }
  }

  // 회원 가입 서비스
  public int create(UserCreateDto dto) {
    log.debug("create({})", dto);
    int result = userDao.insert(dto.toEntity());
    return result;
  }

  // 로그인 서비스
  public User read(UserSignInDto dto) {
    log.debug("read({})", dto);

    User user = userDao.selectByUseridAndPassword(dto.toEntity());

    return user;
  }

  // 이메일 중복 체크
  public boolean checkEmail(String email) {
    log.debug("checkEmail({})", email);
    User user = userDao.selectByEmail(email);
    if (user == null) { // 중복된 이메일이 없을 때, 회원가입 가능!
      return true;
    } else {
      return false;
    }
  }

}