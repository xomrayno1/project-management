package com.tampro.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.app.entity.Employee;
import com.tampro.app.service.EmployeeService;
import com.tampro.app.utils.Constant;
import com.tampro.app.validator.EmployeeValidator;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeValidator employeeValidator;
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == Employee.class) {
			dataBinder.addValidators(employeeValidator);
		}
	}
	
	@RequestMapping
	public String employees(@RequestParam(defaultValue = "0",required = false) int page,
							@ModelAttribute("searchForm") Employee employee
							,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
				Constant.MAX_PAGE_SIZE );
		model.addAttribute("pageEmp", employeeService.getAll(pageable, employee ));
		return "employees/employee-list";
	}
	@GetMapping("/new")
	public String createEmployees(Model map) {
		map.addAttribute("submitForm", new Employee());
		return "employees/employee-action";
	}
	@GetMapping("/edit/{id}")
	public String editEmployees(Model map,@PathVariable("id") long id) {
		Employee employee = employeeService.getEmployeeById(id);
		map.addAttribute("submitForm", employee);
		return "employees/employee-action";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmployees(Model map,@PathVariable("id") long id) {
		try {
			Employee employee = employeeService.getEmployeeById(id);
			if(employee != null) {
				employeeService.delete(employee);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/employees";
	}
	@PostMapping("/save")
	public String createEmployees(@ModelAttribute("submitForm") @Validated Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return "employees/employee-action";
		}
		if(employee.getId() != 0) {
			try {
				employeeService.update(employee);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				employeeService.add(employee);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/employees";
	}
}
