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
		// list = empRepo.findByDeptId(30);

		// list = empRepo.findByFirstName("Steven");

		// list = empRepo.findByFirstNameContaining("te");
		
		// list = empRepo.findByFirstNameLike("%te%");
		
		// list = empRepo.findByFirstNameLike("_teven");
		
		// list = empRepo.findByFirstNameContainingIgnoreCase("te");
		
		// list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("te");
		
		// list = empRepo.findBySalaryGreaterThan(10000);

		// list = empRepo.findBySalaryLessThan(10000);
		
		// list = empRepo.findBySalaryBetween(10000, 15000);

		// list = empRepo.findByHireDateBefore(LocalDate.of(2005, 01, 01));

		// list = empRepo.findByHireDateAfter(LocalDate.of(2006, 01, 01));

		// list = empRepo.findByHireDateBetween(LocalDate.parse("2005-01-01"), LocalDate.parse("2006-01-01"));

		// list = empRepo.findByDeptDname("IT");

		// list = empRepo.findByDeptLocationCity("Seattle");
		
		// list = empRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("a", "b");

		// list = empRepo.findByName("te", "te");
		
		// list = empRepo.findByName2("te", "te");

		// list = empRepo.findByName3("te");

		// list = empRepo.findByDname("IT");

		// list = empRepo.findByCity("seattle");
		
		list = empRepo.findByCountryName("canada");
		list.forEach((x) -> {
			System.out.println(x);
		});
	}
}
