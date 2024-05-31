package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.User;
import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserSignUpController", urlPatterns = { "/user/signup" })
public class UserSignUpController extends HttpServlet {
  private final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

  private final UserService userService = UserService.INSTANCE;

  // 회원 가입에 필요한 요청 처리 메서드.
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("doGet()");

    // 뷰로 요청 전달.
    req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("doPost()");
    String id = req.getParameter("userid");
    String password = req.getParameter("password");
    String email = req.getParameter("email");
    User user = User.builder()
        .userid(id)
        .password(password)
        .email(email)
        .build();

    int result = userService.create(user);
    log.debug("result = {}", result);

    String url = req.getContextPath();
    resp.sendRedirect(url);
  }
}
