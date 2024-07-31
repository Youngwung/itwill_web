package com.itwill.springboot5.dto;

import lombok.Data;

@Data
public class PostSearchRequestDto {
	private String category;
	private String keyword;
	private int p; // 검색 결과 목록의 페이지 번호(0부터 시작)
	// 페이징처리를 해놨기 때문에 나중에 필요할 거임.
}
