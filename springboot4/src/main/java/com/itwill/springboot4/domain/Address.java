package com.itwill.springboot4.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable // 다른 엔터티 클래스(User)의 필드로 포함되는 객체.
public class Address {
	private int postalCode; // 우편번호
	private String city; // 시
	private String street; // 도로명
	private int streetNo;
}
