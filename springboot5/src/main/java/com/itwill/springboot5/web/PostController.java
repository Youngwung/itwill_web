package com.itwill.springboot5.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
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
	public void postList(@RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
		// 서비스 계층의 메서드를 호출 -> 뷰에 포스트 목록을 전달.
		Page<PostListItemDto> list = postServ.read(pageNo, Sort.by("id").descending());
		// 아규먼트로 받은 페이지 번호를 넘김.
		// Sort객체의 by메서드를 호출하고 "id"로 내림차순 
		log.info("list() = {}", list.getContent());
		model.addAttribute("page", list);
		// 페이징 처리를 했다는 표시로 전달하는 데이터의 이름을 page로 변경

		// pagination fragment에서 사용하기 위한 현재 요청 주소 정보
		model.addAttribute("baseUrl", "/post/list");
	}

	// @PreAuthorize("isAthenticated()") //-> role에 상관없이 아이디/비밀번호로만 인증.
	@PreAuthorize("hasRole('USER')") //-> role이 일치하는 아이디/비밀번호 인증.
	@GetMapping("/create")
	public void create() {
		log.info("getCreate()");
	}
	
	@PreAuthorize("hasRole('USER')") //-> role이 일치하는 아이디/비밀번호 인증.
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("postCreate(): {}", dto);
		// 서비스의 create메서드 호출
		postServ.create(dto);
		// 글 목록 페이지로 리다이렉트
		return "redirect:/post/list";
	}

	@PreAuthorize("hasRole('USER')") //-> role이 일치하는 아이디/비밀번호 인증.
	@GetMapping({"/details", "modify"})
	// 처리하는 주소를 배열로 설정할 수 있음.
	public void details(@RequestParam(name="id") Long id, Model model) {
		log.info("details(id = {})", id);
		// 서비스 메서드를 호출
		Post post = postServ.read(id);
		log.info("post = {}", post);
		model.addAttribute("post", post);

		// -> view 이름은, 요청 주소가 details인 경우 datails.html을,
		// 요청 주소가 modify인 경우 modify.html을 찾아감.
	}

	@PreAuthorize("hasRole('USER')") //-> role이 일치하는 아이디/비밀번호 인증.
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Long id) {
		log.info("delete(id = {})", id);
		// 서비스의 delete 메서드 호출
		postServ.delete(id);
		return "redirect:/post/list";
	}

	@PreAuthorize("hasRole('USER')") //-> role이 일치하는 아이디/비밀번호 인증.
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.info("PostUpdateDto(dto = {})", dto);
		// 서비스의 업데이트 메서드 호출
			postServ.update(dto);
		
		return "redirect:/post/details?id="+dto.getId();
		// 수정한 글의 상세보기 페이지로 이동
	}

	@GetMapping("/search")
	public String search(PostSearchRequestDto dto, Model model) {
		log.info("search(dto={})", dto);

		// 서비스 메서드 호출
		Page<Post> page = postServ.search(dto);
		model.addAttribute("page", page);
		model.addAttribute("baseUrl", "/post/search");
		return "post/list";
	}
	
	
	
	
	
}