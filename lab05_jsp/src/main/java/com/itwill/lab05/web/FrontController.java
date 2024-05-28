package com.itwill.lab05.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(name = "frontController", urlPatterns = { "" })
// ? url패턴이 뭐하는거였더라
// => 시작페이지(home.jsp)를 담당하는 서블릿이라 암것도 안쓴겨
// context root(http://localhost:8080/lab05)로 들어오는 요청을 처리하는 서블릿.
// root가 url패턴이라 정해 줄 필요 없음.
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(FrontController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FrontController#doGet");
		log.debug("doGet");
		log.info("doGet");
		log.warn("doGet");
		log.error("doGet");
		log.trace("doGet");
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
