package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService deptServ;

	@Transactional
	@GetMapping("/list")
	public void deptList(Model model) {
		log.info("list()");
		List<Department> deptList = deptServ.read();
		model.addAttribute("deptList", deptList);
	}

	@Transactional
	@GetMapping("/detail")
	public void deptDetail(@RequestParam("id") Integer id, Model model) {
		log.info("detail()");
		Department dept = deptServ.read(id);
		model.addAttribute("dept", dept);
	}

}
