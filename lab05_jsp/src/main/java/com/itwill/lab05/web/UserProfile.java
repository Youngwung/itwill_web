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
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userProfile", urlPatterns = "/user/profile")
public class UserProfile extends HttpServlet {
  Logger log = LoggerFactory.getLogger(UserProfile.class);
  UserService userService = UserService.INSTANCE;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.debug("doGet()");
    HttpSession session = req.getSession();
    String userId = session.getAttribute("signedInUser").toString();
    log.debug("session = {}", userId);
    User user = userService.read(userId);
    req.setAttribute("user", user);
    req.getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
  }

}