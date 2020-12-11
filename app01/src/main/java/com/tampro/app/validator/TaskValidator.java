package com.tampro.app.validator;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.app.entity.Project;
import com.tampro.app.entity.Task;
import com.tampro.app.service.TaskService;

@Component
public class TaskValidator implements Validator{
	@Autowired
	TaskService taskService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Task.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plannedStartDate", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plannedEndDate", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		 
		 
		Task task = (Task) target ;
		 
		if(task.getPlannedStartDate() != null && task.getPlannedEndDate() != null) {
			if(task.getPlannedStartDate().after(task.getPlannedEndDate())) {
				errors.rejectValue("plannedStartDate", "error.dateStartEnd");
			}
		}
		if(task.getActualEndDate() != null && task.getActualStartDate()!= null) {
			if(task.getActualStartDate().after(task.getActualEndDate())) {
				errors.rejectValue("actualStartDate", "error.dateStartEnd");
			}
		}
		if(task.getProject() == null) {
			errors.rejectValue("project", "error.required");
		}
		if(task.getStages() == null) {
			errors.rejectValue("stages", "error.required");
		}
	}

}
