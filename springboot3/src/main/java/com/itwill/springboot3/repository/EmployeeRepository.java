package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot3.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// JPA query method 작성 방법:
	// JPA에서 약속된 키워드들과 엔터티의 필드 이름들을 사용해서
	// 메서드 이름을 (camel 표기법으로) 작성.

	// 부서 번호로 검색
	// select * from employee where department_id = ?
	List<Employee> findByDeptId(Integer id);

	// 이름(firstName)으로 검색:
	// select * from employees where first_name = ?
	List<Employee> findByFirstName(String firstName);

	// 이름에 포함된 단어로 검색:
	// select * from employees where first_name like '%?%'
	List<Employee> findByFirstNameContaining(String keyword);
	// -> Containing: 아규먼트에 '%'를 사용할 필요가 없음

	List<Employee> findByFirstNameLike(String keyword);
	// -> like: 아규먼트에 '%' 또는 '_' 를 사용해야 함.

	// 이름에 포함된 단어로 검색, 단어의 대/소문자 구분 없이 검색.
	// select * from employees where upper(first_name) like upper(?)
	List<Employee> findByFirstNameContainingIgnoreCase(String keyword);

	// 이름에 포함된 단어로 검색, 대소문자 구분없이, 정렬 순서는 이름 내림차순
	// select * from employees where upper(first_name) like upper(?) order by
	// first_name desc;
	List<Employee> findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc(String keyword);

	// 급여가 어떤 값을 초과하는 직원들의 정보
	// select * from employees where salary > ?
	List<Employee> findBySalaryGreaterThan(Integer salary);

	// 급여가 어떤 값 미만인 직원들의 정보
	// ~~ where salary < ?
	List<Employee> findBySalaryLessThan(Integer salary);

	// 급여가 어떤 범위 안에 있는 직원들의 정보(where salary between ? and ?)
	List<Employee> findBySalaryBetween(Integer lowSal, Integer highSal);

	// 입사날짜가 특정 날짜 이전인 직원들의 정보(where hiredate < ?)
	List<Employee> findByHireDateBefore(LocalDate hireDate);

	// 입사날짜가 특정 날짜 이후인 직원들의 정보(where hiredate > ?)
	List<Employee> findByHireDateAfter(LocalDate hireDate);

	// 입사날짜가 특정 날짜 범위안에 있는 직원들의 정보(where hiredate between ? and ?)
	List<Employee> findByHireDateBetween(LocalDate startHireDate, LocalDate endHireDate);

	// 부서 이름으로 직원 검색
	List<Employee> findByDeptDname(String dname);

	// 근무 도시 이름으로 직원 검색(Locations 테이블)
	List<Employee> findByDeptLocationCity(String city);

	// 직무 코드로 직원 검색
	List<Employee> findByJobId(String id);

	// 대소문자 구분 없이 성(lastName) 또는 이름(firstName)이 어떤 문자열을 포함하는 직원 찾기:
	// 메서드 이름 길게 만들어보자
	List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

	// JPQL(Java Persistence Query Language)
	// JPA에서 사용하는 “객체지향(object-oriented)” 쿼리 문법.
	// 테이블 이름과 테이블의 컬럼 이름으로 쿼리 문장을 작성하는 것이 아니라,
	// 엔터티 객체 이름과 엔터티 필드 이름으로 쿼리를 작성하는 문법.
	// alias(별명:컬럼 별명)을 반드시 사용해야 함.
	// 엔터티 이름과 필드 이름은 대소문자를 구분.

	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || ?1 || '%') "
			+ "or upper(e.lastName) like upper('%' || ?2 || '%')")
	List<Employee> findByName(String firstName, String lastName);

	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || :first || '%') "
			+ "or upper(e.lastName) like upper('%' || :last || '%')")
	List<Employee> findByName2(@Param("first") String firstName, @Param("last") String lastName);

	// 바인딩 파라미터 2개가 같은 값인 경우 :param 이 유용함.
	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || :keyword|| '%') "
			+ "or upper(e.lastName) like upper('%' || :keyword|| '%')")
	List<Employee> findByName3(@Param("keyword") String keyword);

	// 부서 이름으로 검색(join 문장)
	@Query("select e from Employee e where e.dept.dname = :dname")
	List<Employee> findByDname(@Param("dname") String dname);

	// 특정 도시(예: Seattle)에 근무하는 직원들 검색
	@Query("select e from Employee e where upper(e.dept.location.city) = upper(:city)")
	List<Employee> findByCity(@Param("city") String city);
	
	// 특정 국가에(예: Canada) 근무하는 직원들 검색
	@Query("select e from Employee e where upper(e.dept.location.country.cName) = upper(:cName)")
	List<Employee> findByCountryName(@Param("cName") String cName);

}
