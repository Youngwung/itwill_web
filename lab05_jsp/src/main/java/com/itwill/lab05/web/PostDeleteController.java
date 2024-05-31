package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);
  private final PostService postService = PostService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // ? 왜 버튼누르는 건데 Get임?
    // * Post는 "<form>태그 안에 있는 정보"를 "서버에서 처리"해야 하는 특별한 상황에서 사용하는 것.
    log.debug("doGet()");

    // 쿼리 문자열에 포함된 요청 파라미터 id 값을 읽음.
    // * modify.js가 주소창에 ?id={int} 형태로 보내준 int값을 읽음
    int id = Integer.parseInt(req.getParameter("id"));
    // *getParameter 메서드는 항상 문자열 타입으로 리턴해 주므로 타입을 맞추기 위해 parseInt 사용.
    log.debug("id = {}", id);

    // 서비스 계층의 메서드를 호출해서 극 삭제 서비스를 실행.
    // * 서비스클래스에서 Dao의 메서드를 이용해서 서비스 구현 후 작성.
    postService.delete(id);

    // 목록 페이지로 이동(redirect)
    // ! 여기서 redirect와 forward의 차이점
    // * redirect: 여기서 적을 url을 찾아가고 해당 url주소를 표시해줌.(주소를 바꿈: post/list)
    // * forward: 호출하기 전인 현재 주소를 url로 표시해줌.(주소를 바꾸지 않음: post/delete)
    String url = req.getContextPath() + "/post/list";
    resp.sendRedirect(url);
  }
}
