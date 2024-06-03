package com.itwill.lab05.filter;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	public void destroy() {
		// 필터 객체가 소멸될 때 WAS가 호출하는 메서
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 인증이 필요한 요청 주소들(예: 새 글, 상세보기, ...) 로그인이 되어있을 때만 실행돼야하는애들
		// 로그인 여부를 확인하고,
		// (1) 로그인되어 있으면, 컨트롤러로 요청을 전달해서 계속 요청을 처리.
		// (2) 로그인되어 있지 않으면, 컨트롤러로 요청을 전달하지 않고 로그인 페이지로 이동.
		// -> 로그인 컨트롤러(UserSignInController)에서 로그인 성공 후 최초 요청 주소로 이동(Redirect).

		HttpServletRequest req = (HttpServletRequest) request;
		String reqUrl = req.getRequestURL().toString(); // 사이트에 접속할 수 있도록 프로토콜부터 써져있음. http://localhost:8080/lab05/post/~~~
		log.debug("reqUrl = {}", reqUrl);

		String contextPath = req.getContextPath();
		log.debug("contextPath = {}", contextPath);

		String reqUri = req.getRequestURI(); // Context Path를 포함한 그 뒤쪽 주소를 출력해줌 /lab05/post/~~~
		log.debug("reqUri = {}", reqUri); // querySring은 알려주지 않음.

		String qs = req.getQueryString(); // 쿼리 스트링 알아낼 수 있음
		log.debug("queryString = {}", qs);

		String target = ""; // 로그인 성공했을 때 이동(redirect)할 요청 주소
		if (qs == null) {
			target = URLEncoder.encode(reqUrl, "UTF-8"); // java.net이 가지고있는 클래스 임포트하셈.
		} else {
			target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8");
			// 특수기호들을 어떤 인코딩 타입으로 변환할 건 지 설정하는거임. %3A 같은거
			// 다른 인코더 쓰면 달라짐.
		}
		log.debug("target = {}", target);

		// 세션에 로그인 정보(signedInUser)
		HttpSession session = req.getSession();
		Object signedInUser = session.getAttribute("signedInUser");
		if (signedInUser == null) { // 로그인 안 되어 있는 상태
			log.debug("로그아웃 상태 --> 로그인 페이지로 이동 --> 로그인 성공 후 target으로 이동");
			String url = req.getContextPath() + "/user/signin?target=" + target;
			((HttpServletResponse) response).sendRedirect(url);
		} else { // 로그인 되어 있는 상태
			log.debug("로그인 상태: {}", signedInUser);
			chain.doFilter(request, response); // 원래 가려고 했던 곳. 처음 요청한 주소 그대로 감.
			// 요청을 계속 처리(-> 요청을 처리하는 서블릿으로 전달.)
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS가 필터 객체를 생성한 후 호출..
	}

}
