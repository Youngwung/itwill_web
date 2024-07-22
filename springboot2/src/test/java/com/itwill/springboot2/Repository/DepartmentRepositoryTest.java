package com.itwill.springboot2.Repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// 테스트 메서드의 순서를 지정하는 애너테이션: 괄호 안에 어떤 메서드를 사용할 지 쓰면 됨.
@SpringBootTest
public class DepartmentRepositoryTest {
	// DEPT 테이블과 매핑되는 엔터티 클래스를 설계, 리포지토리 인터페이스 작성
	@Autowired
	private DepartmentRepository deptRepo;

	// 단위 테스트 클래스 작성.
	@Test
	@Order(1)
	public void test() {
		// DepartmentRepository 객체를 의존성 주입받았음(객체가 생성됨)을 테스트
		assertThat(deptRepo).isNotNull();
		log.info("deptRepo: {}", deptRepo);
	}

	@Transactional
	@Test
	@Order(2)
	public void findAllTest() {
		// dept 테이블 전체 검색 테스트: 행의 개수가 4개이면 성공.
		List<Department> list = deptRepo.findAll();
		assertThat(list.size()).isEqualTo(4);

		list.forEach(System.out::println);
	}
	@Transactional
	@Test
	@Order(3)
	public void findByTest() {
			// 부서번호(deptno 컬럼, id 필드)로 검색 테스트
			// 부서번호가 테이블에 있는 경우:
			Department dept1 = deptRepo.findById(10).orElseThrow();
			// orElseThrow: 값이 있으면 값을 리턴, 값이 없다면 Exception을 던짐.
			assertThat(dept1.getId()).isEqualTo(10);
			log.info("dept1: {}", dept1);

			// OneToMany 관계: 10번 부서의 모든 직원 정보 출력
			List<Employee> employees = dept1.getEmployees();
			employees.forEach(System.out::println);
			
			// 부서번호가 테이블에 없는 경우:
			boolean isEmpty = deptRepo.findById(100).isEmpty();
			assertThat(isEmpty);
	}
}
