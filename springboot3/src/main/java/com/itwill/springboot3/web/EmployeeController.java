package com.itwill.springboot3.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService empServ;
	@GetMapping("/list")
	public void empList(
			@RequestParam(name = "p", defaultValue = "0") int pageNo, 
			Model model) {
		log.info("pageNo = {}", pageNo);
		// 서비스(비즈니스) 계층의 메서드를 호출해서 뷰에 전달할 직원 목록을 가져옴.
		Page<EmployeeListItemDto> empList = empServ.read(pageNo, Sort.by("id"));
		model.addAttribute("page", empList);
	}

	@GetMapping("/detail")
	public void detail(@RequestParam("id") Integer id, Model model) {
		log.info("detail()");
		Employee emp = empServ.read(id);
		log.info("emp={}", emp);
		model.addAttribute("emp", emp);
	}
	
	
}
