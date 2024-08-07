package com.itwill.springboot5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@GetMapping("/signin") 
	// SecurityConfig.securityFilterChanin 메서드에서 
	// formLogin() 메서드의 아규먼트에서 설정한 요청 주소.
	public void signIn() {
		log.info("GET signIn()");
	}

}
