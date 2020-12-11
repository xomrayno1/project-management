package com.tampro.app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.app.entity.Employee;
import com.tampro.app.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> employees(){		 
		try {
			 	
			List<Employee>  response =  employeeService.getAll();
			return new ResponseEntity<List<Employee>>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 
}
