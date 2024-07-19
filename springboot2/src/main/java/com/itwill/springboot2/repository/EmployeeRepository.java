package com.itwill.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.springboot2.domain.Employee;

/*
  Repository<T, ID>
  |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
      |__ JpaRepository<T, ID>
  
  T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입

	==> JpaRepository를 상속받으면
	Crud작업, 페이징처리, 정렬 처리를 자동으로 해준다.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// 꺽쇠(<>)안에 쓴거 <{Entity클래스 이름}, {Entity클래스의 Primary Key의 타입}>

}
