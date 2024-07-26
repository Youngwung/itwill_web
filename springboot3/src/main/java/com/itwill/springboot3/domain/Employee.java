package com.itwill.springboot3.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity @Table(name = "EMPLOYEES") // EMPLOYEES 테이블에 매핑되는 엔터티.
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// 생성자를 private으로 감춤.
// 외부에서 변경하지 못하게 setter를 만들 지 않는 이유와 같음.
@Builder
public class Employee {

	@Id @Column(name = "EMPLOYEE_ID")
	private Integer id;

	// 테이블 컬럼 이름: FIRST_NAME
	// JPA는 카멜 표기법의 엔터티 필드 이름을 테이블 컬럼의 스네이크 표기법의 컬럼 이름으로 자동 변환 해줌.
	// 컬럼 애너테이션 안써줘도 됨.
	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private LocalDate hireDate;

	// JOBS 테이블의 jobId를 참조하는 FK
	// String 타입임. => ManyToOne
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "JOB_ID")
	private Job job;

	private Integer salary;

	private Integer commissionPct; // column: commission_pct

	// Employees테이블의 EMPLOYEE_ID를 참조하는 FK
	// Integer => ManyToOne
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;
	// Employee 엔터티의 @Id 애너테이션이 써있는 필드를 참조함.

	// departments 테이블의 departmentId를 참조하는 FK
	// => ManyToOne
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department dept;
}
