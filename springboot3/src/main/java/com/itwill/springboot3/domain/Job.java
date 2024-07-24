package com.itwill.springboot3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @EqualsAndHashCode
@ToString @Table(name = "JOBS")
public class Job {
	
	@Id @Column(name = "JOB_ID")
	private String id;

	private String jobTitle;

	private Integer minSalary;

	private Integer maxSalary;
}
