package com.itwill.springboot4.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "QUESTIONS")
public class Question {
	
	@Id	// Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동번호 생성 설정
	// -> Oracle: generated as identity, MySQL: autoincrement
	@Column(name = "QID") // 엔터티 필드 이름과 테이블 컬럼 이름이 다를 때.
	private Long id;

	@Basic(optional = false) // not null 제약 조건
	private String title;

	@Basic(optional = false) // not null 제약 조건
	@Column(length = 1000) // varchar2(1000 char)
	private String Content;

}
