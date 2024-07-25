package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Job;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.repository.JobRepository;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("job")
public class JobController {
	private final JobRepository jobRepo;
	private final EmployeeService empServ;

	@GetMapping("/detail")
	public void jobDetail(@RequestParam("id") String id, Model model) {
		log.info("jobRepo = {}", jobRepo);
		Job job = jobRepo.findById(id).orElseThrow();
		model.addAttribute("job", job);
		log.info("empServ = {}", empServ);
		List<EmployeeListItemDto> emps = empServ.readByJobId(id);
		log.info("emps = {}", emps);
		model.addAttribute("emps", emps);
	}
	
}
