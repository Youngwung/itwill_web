package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 디스패쳐 서블릿에서 메서드를 호출할 컨트롤러 컴포넌트.
public class HomeController {
  @GetMapping("/")
  public String home() {
    log.debug("home()");
    return "home"; // 뷰(JSP 파일)의 이름
  }

}
