package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository deptRepo;

	@Test
	public void testDI() {
		assertThat(deptRepo).isNotNull();
		log.info("deptRepo = {}", deptRepo);
	}

	@Test
	@Transactional
	public void testFindAll() {
		List<Department> depts = deptRepo.findAll();
		assertThat(depts.size()).isEqualTo(27);
		depts.forEach((dept) -> {System.out.println(dept);});
	}

	@Test
	@Transactional
	public void testFindById() {
		// dept 테이블 연결 테스트
		Department dept = deptRepo.findById(10).orElseGet(null);
		assertThat(dept.getDname()).isEqualTo("Administration");
		log.info("dept = {}", dept);

		// dept - location 연결 테스트
		Department deptLoc = deptRepo.findById(10).orElseGet(null);
		assertThat(deptLoc.getLocation().getCity()).isEqualTo("Seattle");
		log.info("deptLoc = {}", deptLoc.getLocation());
	}

}
