package com.tampro.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.app.entity.Project;
import com.tampro.app.service.ProjectService;

@Component
public class ProjectValidator implements Validator{
 
	@Autowired
	ProjectService projectService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Project.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plannedStartDate", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plannedEndDate", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		 
		 
		Project project = (Project) target ;
		 
		if(project.getPlannedStartDate() != null && project.getPlannedEndDate() != null) {
			if(project.getPlannedStartDate().after(project.getPlannedEndDate())) {
				errors.rejectValue("plannedStartDate", "error.dateStartEnd");
			}
		}
	}
}
