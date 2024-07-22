package com.itwill.springboot2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentRepository deptRepo;
	public List<Department> read(){
		log.info("read()");
		return deptRepo.findAll();
	}

	public Optional<Department> readById(Integer id) {
		log.info("readById()");
		return deptRepo.findById(id);
	}
}
