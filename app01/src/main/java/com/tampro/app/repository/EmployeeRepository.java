package com.tampro.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.dto.EmployeeTask;
import com.tampro.app.entity.Employee;

@Repository
public interface EmployeeRepository  extends  PagingAndSortingRepository<Employee, Long>{
	@Query(value = "SELECT * FROM EMPLOYEE EMP WHERE  EMP.LAST_NAME LIKE  ?1",nativeQuery = true)
	public	Page<Employee> findByName(Pageable pageable , String name);
	@Query(value = "SELECT * FROM EMPLOYEE EMP  ",nativeQuery = true)
	public List<Employee> getAll();
	
	public	Optional<Employee> findByEmail(String email);
	@Override
	public Page<Employee> findAll();
	
	@Query(value = "  SELECT count(emp.id) as tasksCount,emp.first_name as firstName "
			+ " ,emp.last_name as lastName ,emp.code as code FROM assigned ass	 "
			+ "	inner join employee emp on emp.id = ass.employee_id "
			+ " inner join task tas on tas.id = ass.task_id "
			+ " inner join project pro on pro.id = tas.project_id"
			+ " where pro.id = ?1 group by emp.first_name,emp.last_name , emp.code ",nativeQuery = true)
	public List<EmployeeTask> employeeTasksByProjectId(long projectId); // số lượng task của 1 emp trong 1 projects
	 
}
