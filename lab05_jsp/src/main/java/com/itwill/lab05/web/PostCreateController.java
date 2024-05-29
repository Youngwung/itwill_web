package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name= "postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");
		
		// 새 글 작성 폼(양식)을 작성하는 뷰(jsp)로 이동.(요청전달)
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		/* doPost가 할 일:  
			(1)	요청에 포함된 정보를 읽음.
			(2) 서비스 객체의 메서드를 호출해서 DB 저장.
			(3) 페이지 이동(목록으로 이동하면 좋을듯?)
		*/
		// (1)요청 정보(클라이언트가 보내준 정보: 제목, 내용, 작성자)를 저장한다.
		// getParameter는 String밖에 리턴하지 못하므로 숫자, 날짜 등의 타입을 전부 서블릿에서 변환해야한다.
		// req.getParameter(arg) 메서드의 아규먼트는 요청 파라미터 이름(name 속성의 값)
		// create.jsp에서 클라이언트가 작성하는 태그(input, textarea)의 name="네임속성" 을 적어주어야 한다.
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		Post post = Post.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
		log.debug("post={}", post);
		//TODO (2)서비스 객체 호출해서 DB저장
		
		// (3)포스트 목록 페이지로 이동
		resp.sendRedirect("/lab05/post/list");
		// 리다이렉트는 원래 생략했던 /lab05도 적어주어야한다.
		// 여기서는 forward가 아니라 리다이렉트를 사용해야한다
		//? 
	}
}
