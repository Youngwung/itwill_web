package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.EmployeeRepository;

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

	public Employee read(Integer id) {
		log.info("readById");
		return empRepo.findById(id).orElseGet(null);
	}

}
