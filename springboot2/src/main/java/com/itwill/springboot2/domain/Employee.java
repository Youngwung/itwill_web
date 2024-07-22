package com.itwill.springboot2.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	// @Column(name = "MGR")
	// private Integer manager; // 컬럼이름 mgr
	// * 매니저번호를 매니저이름으로 변경
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MGR")
	private Employee manager;

	private LocalDate hiredate;

	@Column(name = "SAL")
	private Double salary; // 컬럼 이름 sal

	@Column(name = "COMM")
	private Double commission; // 컬럼 이름 comm

	@ToString.Exclude // toString 메서드의 출력 문자열에서 제외.
	//  => Employee클래스의 toString 메서드에 department 필드는 포함되지 않는다.
	// 테이블 JOIN
	@ManyToOne(fetch = FetchType.LAZY)
	// emp.deptno : dept.deptno = N : 1 관계 => ManyToOne
	// Employee에서 작성하고있으므로 기준이 Emp
	// 아규먼트는 기본값.
	// fetch 종류: 
	// FetchType.EAGER
	// FetchType.LAZY
	// EAGER: 바로 조인 테이블을 가져옴
	// LAZY: 조인테이블을 바로 가져오지 않음.
	// LAZY 사용 시 주의점: toString을 사용 시 department는 null인데
	// 이 때 toString에서 사용하려기 때문에 에러가 생길 수 있음.
	// 그래서 ToString.Exclude 에너테이션을 추가한거임
	// EAGER를 사용하더라도 ToString.Exclude를 사용해야하는데,
	// 제외하지 않으면 JOIN하는 과정에서 참조하는 모든 컬럼들의
	// toString을 만들려고 하기 때문에 무한 루프에 빠질 수 있다.

	@JoinColumn(name = "DEPTNO")
	// EMP 테이블에서 DEPT 테이블과 JOIN하는 컬럼 이름.
	private Department department;
}
