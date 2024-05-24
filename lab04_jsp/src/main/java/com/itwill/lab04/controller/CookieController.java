package com.itwill.lab04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Cookie
 */
@WebServlet(name = "cookieController", urlPatterns = { "/cookie" })
public class CookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CookieController::doGet() 호출");

		// 쿠키 객체 생성
		Cookie cookie = new Cookie("Hello", "안녕하세요"); // 이름과 값으로 생성. 둘다 문자열(String)
		
		// 생성된 쿠키 객체를 응답(response) 객체에 포함
		response.addCookie(cookie);

		int count = 1; // 클라이언트가 서버에 방문한 횟수.
		
		// 클라이언트에서 보낸 쿠키를 WAS에서 확인하는 방법:
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			// 쿠키 이름과 쿠키에 저장된 값 출력
			System.out.println(c.getName() + " = " + c.getValue());
			if (c.getName().equals("cnt")) {
				// "cnt"라는 이름의 쿠키가 있으면 쿠키에 저장된 값으로 count 변수를 변경.
				count = Integer.parseInt(c.getValue());
			}
		}
		// 컨트롤러에서 뷰로 변수를 전달하는 방법.
		// visitCount가 뷰에서 사용할 변수 명. count가 사용할 값.
		// count 변수의 값을 request 객체의 속성(attribute)으로 추가
		// ==> JSP(뷰)로 전달할 수 있음.
		request.setAttribute("visitCount", count);
		
		count++; //방문 횟수를 증가
		// 방문 횟수를 저장한 쿠키를 response 객체에 포함.
		Cookie visitCookie = new Cookie("cnt", String.valueOf(count)); // 쿠키 만료 기간 설정. 단위: 초(second)
		visitCookie.setMaxAge(24 * 60 * 60);
		// 쿠키의 만료 기간(maxAge)르 설정하지 않으면, 브라우저가 닫힐 때 쿠키는 만료됨.
		response.addCookie(visitCookie);
		// 첫 요청에 visitCookie는 서버에 오지 않으므로 for문 안에 if문의 조건을 만족하지 못함. 
		// 그래서 count값은 변경되지않고 쿠키의 값을 1로 바꾸고 방문횟수를 증가 시킨다.
		// 두번째 요청은 브라우저가 요청할 때 visitCookie를 실어보내므로 if문의 조건을 만족하여
		// 쿠키의 값을 count 값인 2로 바꿔준다.
		// =====> 첫 방문 1 => 이후 방문할 때 마다 1씩 증가하는 알고리즘 완성.
		
		
		// 뷰로 요청을 전달.
		request.getRequestDispatcher("/WEB-INF/views/cookie.jsp")
				.forward(request, response);
	}

}
