package com.tampro.app.validator;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.app.entity.Employee;
import com.tampro.app.service.EmployeeService;

@Component
public class EmployeeValidator implements Validator{
	@Autowired
	EmployeeService employeService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Employee.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.required");
		 
		Employee employee = (Employee) target ;
		if(!employee.getEmail().isEmpty()) {
			Employee employeeEmail = employeService.getEmployeeByEmail(employee.getEmail()); 
			if(employee.getId() != 0) {
				Employee currentEmployee = employeService.getEmployeeById(employee.getId());
				if(!currentEmployee.getEmail().equals(employee.getEmail())) {
					if(employeeEmail != null) {
						errors.rejectValue("email", "error.exists");
					}
				}
			}else {
				if(employeeEmail != null) {
					errors.rejectValue("email", "error.exists");
				}
			}
		}
		 
	}

 

}
