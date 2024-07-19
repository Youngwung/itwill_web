package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
	ORM(Object Relation Mapping){기술 이름} 
	-> JPA(Java Persistence API){기술 규격(규칙)} 
	-> Hibernate(라이브러리) {JPA에 맞게 구현한 라이브러리}
 */
@Entity // DB 테이블과 매핑하는 자바 객체.(실제로 DB와 연결된 모델 클래스)
// Entity 애너테이션을 사용하기 위한 규칙
// 1. 기본생성자가 반드시 있어야 함.
// 2. Primary Key 컬럼이 무엇인 지 명시해야 함. Id 애너테이션 사용
// 3. 테이블의 컬럼 이름과 필드 이름이 같아야 함.
// ==> 같지 않은 경우 Column애너테이션 사용.
@NoArgsConstructor // 기본 생성자
@Getter
@ToString
@EqualsAndHashCode
@Table(name="EMP") // 클래스 이름과 실제 테이블의 이름이 다를 때 사용.
// 실제 테이블 이름 "EMP"
public class Employee {
	// 컬럼이름과 다른 부분을 주석으로 적었음.

	@Id // Primary Key
	@Column(name = "EMPNO") // 테이블 컬럼 이름과 다른 경우
	private Integer id;	// 컬럼 이름 empno
	
	private String ename;
	private String job;	

	@Column(name = "MGR")
	private Integer manager; // 컬럼이름 mgr

	private LocalDate hiredate;

	@Column(name = "SAL")
	private Double salary; // 컬럼 이름 sal

	@Column(name = "COMM")
	private Double commission; // 컬럼 이름 comm
	
	private Integer deptno; 
}
