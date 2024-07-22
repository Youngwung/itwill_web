package com.itwill.springboot2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
	private final DepartmentService deptServ;
	@GetMapping("/list")
	public void deptList(Model model) {
		log.info("deptList()");
		log.info("deptServ = {}", deptServ);
		List<Department> deptList = deptServ.read();
		model.addAttribute("dept", deptList);
	}

	@GetMapping("/detail")
	public void deptDetail(@RequestParam("id") Integer id, Model model) {
		log.info("deptDetail");
		Optional<Department> deptOp = deptServ.readById(id);
		Department dept = deptOp.orElseGet(null);
		model.addAttribute("dept", dept);
		model.addAttribute("deptEmp", dept.getEmployees());
	}
	
}
