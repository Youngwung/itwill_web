package com.itwill.springboot3.dto;

import com.itwill.springboot3.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeListItemDto {
	private Integer employeeId;
	private String employeeName; 
	// 테이블은 first와 last로 나뉘어져 있지만 dto에선 합칠거임
	private String phoneNumber;
	private String jobTitle;
	private String departmentName;

	public static EmployeeListItemDto fromEntity(Employee entity) {
		
		// Job 또는 Department가 null일 경우 처리를 하기 위해서.

		String deptName = (entity.getDept() != null) ? entity.getDept().getDname() : null;
		String jobTitle = (entity.getJob() != null) ? entity.getJob().getJobTitle() : null;
		
		return EmployeeListItemDto.builder()
			.employeeId(entity.getId())
			.employeeName(entity.getFirstName() + " " + entity.getLastName())
			.phoneNumber(entity.getPhoneNumber())
			.jobTitle(jobTitle)
			// Job은 객체라 Jobtitle 꺼낼 수 있음
			.departmentName(deptName)
			//dept는 객체라 dname을 꺼낼 수 있음.
			.build();
	}
	
}
