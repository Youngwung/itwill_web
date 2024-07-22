package com.itwill.springboot2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService empServ;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		
		List<Employee> employees = empServ.read();
		model.addAttribute("employees", employees);
	}

	@GetMapping("/detail")
	public void detail(@RequestParam("id") Integer id, Model model) {
		log.info("detail()");
		Optional<Employee> empOp = empServ.readById(id);
		log.info("emp = {}", empOp);
		Employee emp = empOp.orElseGet(null);
		model.addAttribute("emp", emp);
	}
	

}
