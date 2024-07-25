package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.dto.EmployeeListItemDto;
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
	public void deptList(
				@RequestParam(name="p", defaultValue = "0") int pageNo, 
				Model model) {
		log.info("list()");
		Page<Department> deptList = deptServ.read(pageNo, Sort.by("id"));
		model.addAttribute("page", deptList);
	}

	@Transactional
	@GetMapping("/detail")
	public void deptDetail(@RequestParam("id") Integer id, Model model) {
		log.info("detail()");
		Department dept = deptServ.read(id);
		model.addAttribute("dept", dept);
		List<EmployeeListItemDto> emps = deptServ.readByDeptid(id);
		model.addAttribute("emps", emps);
	}

}
