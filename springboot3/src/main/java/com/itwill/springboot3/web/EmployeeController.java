package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.repository.EmployeeRepository;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeRepository empRepo;
	private final EmployeeService empServ;
	@GetMapping("/list")
	public void empList(Model model) {
		log.info("list()");
		List<Employee> empList = empServ.read();
		model.addAttribute("empList", empList);
	}

	@GetMapping("/detail")
	public void detail(@RequestParam("id") Integer id, Model model) {
		log.info("detail()");
		Employee emp = empServ.read(id);
		log.info("emp={}", emp);
		model.addAttribute("emp", emp);
	}
	
	
}
