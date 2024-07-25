package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.EmployeeListItemDto;
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

	public Department read(Integer id) {
		log.info("readById");
		return deptRepo.findById(id).orElseGet(null);
	}

	public List<EmployeeListItemDto> readByDeptid(Integer id){
		log.info("readByDeptId(id = {})", id);

		List<Employee> list = empRepo.findByDeptId(id);
		return list.stream().map(EmployeeListItemDto::fromEntity).toList();
	}
}
