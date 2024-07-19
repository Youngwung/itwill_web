package com.itwill.springboot2.Repository;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.DepartmentRepository;
import com.itwill.springboot2.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired // 의존성 주입: (DI: dependency injection), 제어의 역전(Ioc: Inversion of Control)
	private EmployeeRepository empRepo;
	@Autowired
	private DepartmentRepository deptRepo;
	
	@Test
	public void test() {
		assertThat(empRepo).isNotNull(); // empRepo 객체가 null이 아니면 테스트 성공.
		log.info("{}", empRepo); // {}를 써서 toString 호출.
	}

	@Test
	public void findAllTest() {
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);

		for (Employee e : list) {
			System.out.println(e);
		}
	}

	@Test
	public void findByTest() {
		// TODO: 사번으로 검색하는 메서드를 찾아서 단위 테스트 코드 작성.
		Optional<Employee> emp = empRepo.findById(7369);
		assertThat(emp).isNotNull();
		System.out.println(emp);
	}

	// TODO: DEPT 테이블과 매핑되는 엔터티 클래스를 설계, 리포지토리 인터페이스 작성
	// 단위 테스트 클래스 작성.
	@Test
	public void findAllDeptTest() {
		List<Department> list = deptRepo.findAll();
		assertThat(list).isNotNull();
		System.out.println(list);
	}

}
