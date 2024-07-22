package com.itwill.springboot2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository empRepo;

	public List<Employee> read() {
		log.info("read()");
		return empRepo.findAll();
	}

	public Optional<Employee> readById(Integer id){
		log.info("readById");
		return empRepo.findById(id);
	}
}
