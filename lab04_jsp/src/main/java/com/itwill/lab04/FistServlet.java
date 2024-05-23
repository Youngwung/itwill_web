package com.itwill.lab04;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FistServlet
 */
public class FistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FistServlet() {
		super(); // 생략 가능
		System.out.println("FisrtServlet 생성...");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// WAS(Wab Application Server): 웹 요청(request)/ 응답(response)을 처리하는 프로그램. Tomcat.
	// doGet(): get 방식의 요청이 왔을 때 WAS가 호출하는 메서드.
	// doPost(): post 방식의 요청이 왔을 때 WAS가 호출하는 메서드.
	// 파라미터 request: 클라이언트가 서버로 보낸 요청의 정보, 기능(메서드)들을 갖는 객체.(HttpServletRequest 타입)
	// 파라미터 response: 서버가 클라이언트로 보낼 으앋브이 정보, 기능들을 갖는 객체.(HttpServletResponse 타입)
	@Override // HttpServlet클래스가 가지고있는 메서드 재정의.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FirstServlet::doGet() 호출");

		response.setContentType("text/html; charset=UTF-8"); // 페이지에서 한글이 깨지는 현상을 해결하는 코드

		response.getWriter()
				.append("<!doctype html>")
				.append("<html>")
				.append("<head>")
				.append("<meta charset='UTF-8' />")
				.append("<title>Servlet</title>")
				.append("</head>")
				.append("<body>")
				.append("<h1>첫번째 Servlet</h1>")
				.append("<a href = \"/lab04\">인덱스 페이지</a>")
				.append("</body>")
				.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override // HttpServlet클래스가 가지고있는 메서드 재정의.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FirstServlet::doPost() 호출");
		doGet(request, response);
	}

}
