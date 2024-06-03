package com.itwill.lab05.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.repository.Post;
import com.itwill.lab05.service.PostService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "postCreateController", urlPatterns = { "/post/create" })
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostCreateController.class); // 로그 객체 생성
	private final PostService postService = PostService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doGet()");

		// 새 글 작성 폼(양식)을 작성하는 뷰(jsp)로 이동.(요청전달)
		req.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("doPost()");
		/*
		 * doPost가 할 일:
		 * (1) 요청에 포함된 정보를 읽음.
		 * (2) 서비스 객체의 메서드를 호출해서 DB 저장.
		 * (3) 페이지 이동(목록으로 이동하면 좋을듯?)
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
		// (2)서비스 객체 호출해서 DB저장
		int result = postService.create(post);
		log.debug("result = {}", result);
		// (3)포스트 목록 페이지로 이동
		// resp.sendRedirect("/lab05/post/list");
		String url = req.getContextPath() + "/post/list";
		// 이렇게 직접 Context root를 써주는 것 보다 코드로 작성해서 정확한 주소를 입력해 주는게 안전하다.
		// Context root는 프로젝트를 만든 후에도 변경이 가능하기 때문에 코드로 작성하는게 맞는듯.
		log.debug("redirect = {}", url);
		resp.sendRedirect(url);
		// 리다이렉트는 원래 생략했던 /lab05도 적어주어야한다.
		// 이 패턴이 PRG(Post-Redirect-Get) 방식이다.
		// * 클라이언트가 보내준 정보를 처리해야 할 때 가장 많이 사용하는 패턴.

		// !여기서는 forward가 아니라 리다이렉트를 사용해야한다
		// ? forward를 사용하면 서블릿을 통해 url로 이동하는게 아니라
		// ? 직접 url로 이동하기 때문에 서블릿을 통해 전달했던 변수들이 지정이 안된다.
		// ? 이 부분 같은 경우에는 서블릿이 만들어 주었던 {posts} 리스트를 사용할 수 없어
		// ? 테이블을 출력하지 않는다. 이건 원하는 방식이 아님.
		// ! post방식 요청은 항상 redirect를 사용한다고 생각하면 될듯.
	}
}
