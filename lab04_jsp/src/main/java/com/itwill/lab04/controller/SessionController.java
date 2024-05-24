package com.itwill.lab04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SessionController
 */
@WebServlet(name="sessionController", urlPatterns = {"/session"})
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SessionController::doGet() 호출");
		
		// 서버가 저장하는, 클라이언트(브라우저)마다 매핑되어 있는 세션 객체 찾음:
		HttpSession session = request.getSession(); // 리턴타입 HttpSession
		// getSession() 메서드를 호출:
		// (1) 클라이언트가 JSESSIONID 쿠키를 보낸 경우, 세션 아이디를 사용해서 세션 객체 찾음.
		// (2) 클라이언트가 JSESSIONID 쿠키를 보내지 않은 경우, 새로운 세션 객체를 생성
		// 응답을 보낼 때 JSESSIONID 쿠키가 클라이언트로 전송됨.
		
		System.out.println(session);
		// 크롬에서의 session과 마이크로엣지의 session의 주소값이 서로 다름.
		// 크롬: @6dc4b853, 엣지: @4d90fc3f
		// 같은 브라우저에서 여러번 호출해도 같은 값이 나오지만, 다른 브라우저면 세션의 주소는 다르다.
		
		session.setAttribute("nickname", "관리자"); // 서버가 켜져있는 동안 서버의 메모리에 저장.
		// 그래서 따로 설정하지 않으면 30분 후에 정보가 사라짐.
//		session.setMaxInactiveInterval(10); // 초단위. 세션 만료 기간 (정확한 단어 뜻은 비활성 시간) 
		
		// 뷰로 이동
		request.getRequestDispatcher("/WEB-INF/views/session.jsp")
			.forward(request, response);
	}

}
