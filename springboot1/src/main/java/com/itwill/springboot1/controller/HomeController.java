package com.itwill.springboot1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	

}
