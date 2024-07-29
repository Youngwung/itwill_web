package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postServ;
	
	@GetMapping("/list")
	public void postList(Model model) {
		// 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록을 전달.
		List<PostListItemDto> list = postServ.read();
		log.info("list() = {}", list);
		model.addAttribute("posts", list);
	}

	@GetMapping("/create")
	public void create() {
		log.info("getCreate()");

	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("postCreate(): {}", dto);
		// 서비스의 create메서드 호출
		postServ.create(dto);
		// 글 목록 페이지로 리다이렉트
		return "redirect:/post/list";
	}
	
}