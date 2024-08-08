package com.itwill.springboot5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot5.domain.Member;
import com.itwill.springboot5.dto.MemberSignUpDto;
import com.itwill.springboot5.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberServ;
	
	@GetMapping("/signin") 
	// SecurityConfig.securityFilterChanin 메서드에서 
	// formLogin() 메서드의 아규먼트에서 설정한 요청 주소.
	public void signIn() {
		log.info("GET signIn()");
	}

	@GetMapping("/signup")
	public void signUp() {
		log.info("Get singUp()");
	}

	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto) {
		log.info("Post signUp(dto = {})", dto);

		// 서비스 계층의 메서드 호출해서 회원가입 정보들을 DB에 저장.

		Member member = memberServ.create(dto);
		log.info("member = {}", member);
		// 회원가입 성공 시 로그인 페이지로 이동.
		return "redirect:/member/signin";
	}

	

}
