package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.dto.EmployeeTask;
import com.tampro.app.entity.Employee;

public interface EmployeeService {
	
	public Employee getEmployeeById(long id);
	
	public Employee getEmployeeByEmail(String email);
	
	public List<Employee> getAll();
	
	public Page<Employee> getAll(Pageable pageable ,Employee employee );
 
	public void add(Employee employee) throws Exception;
	
	public void update(Employee employee) throws Exception;
	
	public void delete(Employee employee) throws Exception;
	
	public List<EmployeeTask> employeeTasksByProjectId(long projectId);
}
