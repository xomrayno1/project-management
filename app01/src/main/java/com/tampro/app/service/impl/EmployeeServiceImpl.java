package com.tampro.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tampro.app.dto.EmployeeTask;
import com.tampro.app.entity.Employee;
import com.tampro.app.repository.EmployeeRepository;
import com.tampro.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id).get();
	}

	@Override
	public Page<Employee> getAll(Pageable pageable, Employee employee  ) {
		// TODO Auto-generated method stub
		 
		Page<Employee>	pageEmp = null;
		if(employee.getLastName() != null  ) {
			 pageEmp = empRepo.findByName(pageable, "%"+employee.getLastName()+"%");
		}else {			 	
			 pageEmp = empRepo.findAll(pageable);
			 
		}
		 return pageEmp;
		 
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return empRepo.getAll() ;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return empRepo.findByEmail(email).orElse(null);
	}

	@Override
	public void add(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		try {
			employee.setUpdateDate(new Date());
			employee.setCreateDate(new Date());
			empRepo.save(employee);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void update(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		try {
			employee.setUpdateDate(new Date());
			 
			empRepo.save(employee);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void delete(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		try {
 		 
			empRepo.delete(employee);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public List<EmployeeTask> employeeTasksByProjectId(long projectId) {
		// TODO Auto-generated method stub
		return empRepo.employeeTasksByProjectId(projectId);
	}
 

}
