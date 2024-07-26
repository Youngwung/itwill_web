package com.itwill.springboot4.domain;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="USERS")
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ? 아마 자동생성 PK 인듯?
	private Long id;
	
	@NaturalId // Unique 제약조건
	@Basic(optional = false) // not null 제약 조건
	private String username;

	@Basic(optional = false) // not null 제약 조건
	private String password;

	@Enumerated(EnumType.STRING)
	// enum클래스를 컬럼으로 만들면 Check 제약조건이 들어감.
	private Gender gender;

	//varchar2 (1000 char)로 만듬
	@Column(length = 1000)
	private String memo;

	@Embedded // @Embeddable로 선언된 객체를 포함 => 생략 가능
	private Address address;
	
}
