package com.itwill.springboot3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("job")
public class JobController {
	private final EmployeeRepository empRepo;

	@GetMapping("/detail")
	public void jobDetail(@RequestParam("id") String id) {
		log.info("empRepo = {}", empRepo);
		
	}
	
}
