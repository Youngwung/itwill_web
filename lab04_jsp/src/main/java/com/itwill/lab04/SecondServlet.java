package com.itwill.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; 
// 생성하자마자 생긴 불필요한 import 문? => web.xml 설정 대신 여기서 설정할 수 있음

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SecondServlet
 */
// 서블릿 URL(요청 주소)  매핑 방법:
// 1. web.xml(배포 관리자, deployment descriptor)에서 <servlet>, <servlet-mapping>로 설정하거나,
// 2. 서블릿 클래스에서 @WebServlet 에너테이션으로 설정.
// (주의) 둘 중 단 하나만 설정해야 함.
@WebServlet(name="secondServlet", urlPatterns = {"/ex2"})
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SecondServlet::doGet() 메서드 호출");
		
		// WAS(웹 어플 서버)가 클라이언트로 보내는(response) 컨텐트 타입 설정:
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append("<html>")
			.append("<body>")
			.append("<h1>두번째 Servlet</h1>")
			.append("<a href='/lab04'>인덱스 페이지</a>")
			.append("</body>")
			.append("</html>");
		
	}

}
