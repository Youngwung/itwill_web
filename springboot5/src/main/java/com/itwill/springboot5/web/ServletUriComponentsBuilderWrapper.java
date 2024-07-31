package com.itwill.springboot5.web;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ServletUriComponentsBuilderWrapper {
	
	// thymeleaf 3.x 버전에서 ServletUriComponentsBuilder 타입을 사용할 수 없어서 만든 클래스
	public static ServletUriComponentsBuilder fromCurrentRequest() {
		return ServletUriComponentsBuilder.fromCurrentRequest();
	}

}
