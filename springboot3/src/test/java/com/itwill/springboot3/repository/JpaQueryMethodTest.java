package com.itwill.springboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaQueryMethodTest {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	@Transactional
	public void test() {
		List<Employee> list;
		list = empRepo.findByDeptId(30);
		list.forEach((x) -> System.out.println(x));

		list = empRepo.findByFirstName("Steven");
		list.forEach((x) -> System.out.println(x));

		list = empRepo.findByFirstNameContaining("te");
		list.forEach((x) -> System.out.println(x));
		
		list = empRepo.findByFirstNameLike("%te%");
		list.forEach((x) -> System.out.println(x));
		
		list = empRepo.findByFirstNameLike("_teven");
		list.forEach((x) -> System.out.println(x));
		
		list = empRepo.findByFirstNameContainingIgnoreCase("te");
		list.forEach((x) -> System.out.println(x));

		list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("te");
		list.forEach((x) -> System.out.println(x));
	}
}
