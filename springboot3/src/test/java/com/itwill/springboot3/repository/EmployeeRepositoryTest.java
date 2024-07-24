package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class EmployeeRepositoryTest {
	// 테스트 클래스에서 Autowired를 사용하는 이유:
	// private final 생성자를 만들어도 테스트를 진행하는
	// SpringBootTest는 기본 생성자만 사용하기 때문에 만들어도 의존성 주입이 되지 않음.
	// Autowired를 사용해서 의존성 주입을 받아야 한다.
	@Autowired
	private EmployeeRepository empRepo;	

	@Test
	public void testDependencyInjection() {
		// EmployeeRepository 객체를 의존성 주입을 받을 수 있는 지 테스트.
		assertThat(empRepo).isNotNull();
		log.info("empRepo = {}", empRepo);
		/* 
		 * Repository<T, ID>
		 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
		 * 			|__ JpaRepository<T, ID>
		 * 			    |__ EmployeeRepository<T, ID>
		 * 							 |__ SimpleJpaRepository<T, ID>
		 */
	}

	@Test
	@Transactional
	public void testFindAll() {
		// employees 테이블 전체 검색 테스트
		List<Employee> emps = empRepo.findAll();
		// empRepo는 count함수도 가지고 있음. 이걸 활용해도 될듯.
		assertThat(emps.size()).isEqualTo(107);
		emps.forEach((emp) -> {System.out.println(emp);});
		log.info("employees[0] = {}", emps.get(0));
	}

	@Test
	@Transactional
	// fetch타입을 LAZY로 할 경우 테스트 메서드에 Transactional 애너테이션을 추가해서 에러를 막아야 함.
	public void testFindById() {
		// 테이블에 id(사번)가 존재하는 경우:
		// 1. EmployeeRepository.findById() 메서드 테스트
		Employee emp = empRepo.findById(101).orElseGet(null);
		log.info("emp={}", emp);
		// 2. Employee 테이블 - JOBS 테이블의 관계 테스트
		log.info("emp.job={}", emp.getJob());
		// 3. Employee 테이블 - Employees 테이블 간의 관계 테스트(MANAGER_ID = EMPLOYEE_ID)
		log.info("emp.manager={}", emp.getManager());
		// 4. Employee 테이블 - departments 테이블 간의 관계 테스트(DEPARTMENT_ID = DEPARTMENT_ID)
		log.info("emp.department={}", emp.getDept());
		// 5. Employee 테이블 - departments - Locations 테이블 간의 관계 테스트
		log.info("emp.dept.loc={}", emp.getDept().getLocation());
		// 6. Locations - COUNTRIES 테이블 테스트
		log.info("dept.loc.country={}", emp.getDept().getLocation().getCountry());
		// 7. COUNTRIES - REGIONS 테이블 테스트
		log.info("Region = {}", emp.getDept().getLocation().getCountry().getRegion());
	}

}
