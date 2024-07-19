package com.itwill.springboot2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
