package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.DepartmentDetailDto;
import com.itwill.springboot3.repository.DepartmentRepository;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {

	private final DepartmentRepository deptRepo;
	private final EmployeeRepository empRepo;

	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 바꿔줌.
	public Page<Department> read(int pageNo, Sort sort) {
		log.info("read(pageNo = {}, sort = {})", pageNo, sort);
		Pageable pageable = PageRequest.of(pageNo, 5, sort);
		return deptRepo.findAll(pageable);
	}
	
	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 바꿔줌.
	public DepartmentDetailDto read(Integer id) {
		log.info("readById");
		Department dept = deptRepo.findById(id).orElseThrow();
		List<Employee> employees = empRepo.findByDeptId(id);
		return DepartmentDetailDto.fromEntity(dept, employees);
	}

	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 바꿔줌.
	public DepartmentDetailDto read(String dname) {
		log.info("read(dname = {})", dname);

		// Department 엔터티 객체 생성:
		Department department = Department.builder().dname(dname).build();
		// => dname 필드만 초기화하고 나머지 필드들은 null인 객체 생성

		//Example 객체 생성:
		Example<Department> example = Example.of(department);
		// 예제 객체: dname을 가지고 있는 예제 객체 생성

		// Example 객체를 findOne() 또는 findAll() 메서드의 아규먼트로 전달:
		// 같은 이름을 갖는 부서가 여러개면 findAll(), 한 개면 findOne()
		// 우리는 부서이름이 겹치지 않음 => findOne()
		Department resultDept= deptRepo.findOne(example).orElseThrow();
		// findOne의 리턴 타입은 Optional<generic>
		log.info("resultDept id = {}", resultDept.getId());
		// ? department 객체에는 dname을 제외한 필드들은 null인데 getId를 쓰면 null아님?
		// => example 객체가 dname으로 검색해서 id를 객체에 넣어줌.

		// deptId로 직원 검색
		List<Employee> employees = empRepo.findByDeptId(resultDept.getId());
		
		return DepartmentDetailDto.fromEntity(department, employees);
	}

}
