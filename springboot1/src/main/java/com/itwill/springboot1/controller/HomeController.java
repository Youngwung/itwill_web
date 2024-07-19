package com.itwill.springboot1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot1.dto.Author;
import com.itwill.springboot1.dto.Book;

import lombok.extern.slf4j.Slf4j;




@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String homeController(Model model) {
		log.info("home()");
		LocalDateTime now = LocalDateTime.now(); // 시스템 현재 시간
		model.addAttribute("currentTime", now);

		Author author = Author.builder().firstName("찰스").lastName("다윈").build();
		Book book = Book.builder().id(1).title("종의 기원").author(author).build();
		log.debug("book: {}", book);
		model.addAttribute("book", book);
		return "index";
		// -> 뷰의 이름을 리턴.
		// -> src/main/resources/templates.index.html
	}

	@GetMapping("/book/list")
	public void bookList(Model model) {
		// return 타입이 void인 경우 뷰의 이름은 요청 주소와 같음.
		log.debug("bookList()");

		// 도서 목록 더미 데이터를 저장하기 위한 리스트.
		ArrayList<Book> list = new ArrayList<>();

		// 더미 데이터 5개를 리스트에 저장.
		for (int i = 1; i <= 5; i++) {
			Book book = Book.builder()
							.id(i)
							.title("Title-" + i)
							.author(Author.builder().firstName("Name-"+i).lastName("LastName").build())
							.build();
			list.add(book);
		}

		Book b = Book.builder().id(10).title("종의 기원").build(); // author = null
		list.add(b);
		
		// 도서 목록을 뷰에 전달.
		model.addAttribute("bookList", list);
	}
	
	@GetMapping("/book/details")
	public void bookDetails(@RequestParam(name="id") int id, Model model) {
		// 요청 파라미터 id 값을 찾고, 해당 id를 갖는 Book 객체를 만듦.
		log.info("details(id = {})", id);
		Book book = Book.builder().id(id).title(id + " 제목")
		.author(Author.builder().firstName(id+"").lastName("의 작가").build()).build();
		model.addAttribute("book", book);
		// 모델에 Book 객체를 속성(attr)으로 저장. 뷰로 전달
	}

	// path variable을 포함하는 요청을 처리하는 메서드.
	@GetMapping("/book/details/{id}")
	// PathVariable 매핑 주소 작성
	public String bookDetails2(@PathVariable(name="id") int id, Model model) {
		// PathVariable을 아규먼트로 받음. 애너테이션의 속성값은 주소의 변수이름과 같아야함.
		log.info("bookDetails2(id = {})", id);
		// PathVariable로 전달받은 id로 book객체 생성
		Book book = Book.builder().id(id).title(id + " 제목")
		.author(Author.builder().firstName(id+"").lastName("의 작가").build()).build();
		// model로 뷰에 전달.
		model.addAttribute("book", book);
		// 쿼리스트링 처리 메서드에서 전달한 변수와 같은 이름으로 보냄.
		
		// ==> 다른 변수로 하면 details.html에서 book객체가 없어서 에러뜸.
		// 요청주소에 따라 다른 방법으로 모델을 전달하는 거임.

		// void로 사용 시 뒤에 id.html을 주소로 사용함.
		// 모든 id에 대해 html파일을 만들 수 없으므로 String으로 명확한 html을 찾아주어야함.
		return "/book/details";
	}
	
	

}
