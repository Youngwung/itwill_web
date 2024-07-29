package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
}