package com.itwill.springboot2.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "DEPT")
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Department {
	@Id
	@Column(name = "DEPTNO")
	private Integer id;
	private String dname;
	@Column(name = "LOC")
	private String location;

	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	// mappedBy: Employee 엔터티에서 @OneToMany 애너테이션이 설정된 필드 이름.
	// mappedBy의 값: 관계를 맺는 테이블의 ManyToOne애너테이션을 갖는 필드 이름
	private List<Employee> employees;
	// 한 개의 부서 번호는 여러개의 직원을 가지고 있으므로 LIST<>
}
