package com.itwill.lab05.web;

import java.io.IOException;
import java.net.URLEncoder;

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

    // 로그인 화면에서 사용자가 입력(전송)한 userid, password 값을 읽음.
    String userid = req.getParameter("userid");
    String password = req.getParameter("password");

    // 서비스 계층의 메서드를 호출해서 사용자가 입력한 정보를 전달.
    // 로그인 성공 여부를 판단.
    User user = userService.signIn(userid, password);

    // 로그인 성공이면 타겟(target) 페이지, 그렇지 않으면 다시 로그인 페이지로 이동:
    String target = req.getParameter("target"); // 요청 파라미터를 읽는 방법
    log.debug("target = {}", target);

    if (user != null) { // 데이터베이스 user 테이블에서 일치하는 사용자 정보가 있는 경우.
      // *세션에 로그인 정보를 저장. 페이지를 이동.
      HttpSession session = req.getSession();
      // 최초 요청을 제외한 모든 요청에는 req에 세션 정보를 가지고있다. 클라이언트가 보내줌.
      session.setAttribute("signedInUser", user.getUserid());

      if (target == null || target.equals("")) { // 원래있던 페이지(타겟)가 홈페이지인 경우.
        String url = req.getContextPath();
        resp.sendRedirect(url);
      } else { // 원래 있던 페이지가 홈페이지가 아닌경우.
        resp.sendRedirect(target);
      }

    } else { // 테이블에서 일치하는 사용자 정보가 없는 경우.
      // * 다시 로그인 페이지로 이동.
      // 로그인 실패 시 그냥 로그인 페이지로 돌아가기만 하면 target정보가 사라짐.
      // 이 때도 타겟정보가 남아있도록 쿼리스트링으로 남겨줌.
      // 여기서 target은 인코딩이 풀려 특수문자가 그대로 있기 때문에 다시 인코드 설정을 해줌.
      // result=f 쿼리스트링을 추가해 로그인을 한 번 실패했다는 정보를 넘겨줌.
      String url = req.getContextPath() + "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
      resp.sendRedirect(url);
    }

  }
}
