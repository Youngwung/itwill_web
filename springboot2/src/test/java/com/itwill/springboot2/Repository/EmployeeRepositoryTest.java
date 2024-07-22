package com.itwill.springboot2.Repository;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired // 의존성 주입: (DI: dependency injection), 제어의 역전(Ioc: Inversion of Control)
	private EmployeeRepository empRepo;

	
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


	@Transactional
	// org.springframework.transaction.annotation.Transactional;
	@Test
	public void findByTest() {
		// 사번으로 검색하는 메서드를 찾아서 단위 테스트 코드 작성.
		Optional<Employee> emp1 = empRepo.findById(7369);
		// Optional: null일 수도 있는 객체를 저장할 때 JPA가 사용하는 객체 타입. null이어도 에러가 발생하지 않음.
		Employee smith = emp1.get();
		assertThat(smith).isNotNull();
		// Optional 객체가 null이 아닌게 확실하다면 Optional객체의 메서드인 get()을 사용할 수 있다.
		// 이 때 emp가 null이면 nullPointerException 발생.
		assertThat(smith.getEname()).isEqualTo("SMITH");
		log.info("smith={}", smith);
		log.info("dept={}", smith.getDepartment());
		log.info("manager={}", smith.getManager());
		System.out.println(smith);

		// 사번이 테이블에 없는 경우: 
		Optional<Employee> emp2 = empRepo.findById(1000);
		Employee none = emp2.orElseGet(() -> null);
		// orElseGet(): 값이 있으면 그대로 리턴. 값이 없으면 아규먼트에 있는 함수의 리턴값을 리턴해줌.
		assertThat(none).isNull();
		// assertThat(emp).isNotNull();
		// System.out.println(emp);
	}

	// 매니저 번호를 매니저 이름으로 출력하기 위한 테스트
	@Transactional
	@Test
	public void findManagerTest() {
		// 사번이 7369인 직원 정보 검색:
		Optional<Employee> emp1 = empRepo.findById(7369);
		Employee smith = emp1.orElseThrow();
		assertThat(smith.getId()).isEqualTo(7369);
		log.info("smith = {}", smith);

		Employee mgr = smith.getManager();
		assertThat(mgr.getId()).isEqualTo(7902); // 7369 직원의 매니저는 7902
		log.info("mgr = {}", mgr);
	}
	
}
