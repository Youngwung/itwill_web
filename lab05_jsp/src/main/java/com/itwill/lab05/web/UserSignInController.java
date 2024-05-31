package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userSignInController", urlPatterns = { "/user/signin" })
public class UserSignInController extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);

  private final UserService userService = UserService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("doGet()");

    req.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("doPost()");
    // TODO: 로그인컨트롤러 doPost작성
  }
}
