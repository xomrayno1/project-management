package com.tampro.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.app.entity.UserAccount;
import com.tampro.app.service.UserAccountService;

@Component
public class UserAccountValidator  implements Validator{
	@Autowired
	UserAccountService userAccountService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(UserAccount.class);
	}
  
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.required");
		UserAccount userAccount = (UserAccount) target ;
		
		
		if(!userAccount.getEmail().isEmpty() && !userAccount.getUsername().isEmpty()) {
			UserAccount accountEmail = userAccountService.findByEmail(userAccount.getEmail()); 
			UserAccount accountUser = userAccountService.getByUsername(userAccount.getUsername());
			if(userAccount.getId() != 0) {
				UserAccount currentAccount = userAccountService.getAccountById(userAccount.getId());
				if(!currentAccount.getEmail().equals(userAccount.getEmail())) {
					if(accountEmail != null) {
						errors.rejectValue("email", "error.exists");
					}
				}
				if(!currentAccount.getUsername().equals(userAccount.getUsername())) {
					if(accountUser != null) {
						errors.rejectValue("username", "error.exists");
					}
				}
			}else {
				if(accountEmail != null) {
					errors.rejectValue("email", "error.exists");
				}
				if(accountUser != null) {
					errors.rejectValue("username", "error.exists");
				}
			} 
		}
		if(userAccount.getRoles() == null || userAccount.getRoles().isEmpty() || userAccount.getRoles().size() <= 0   ) {
			errors.rejectValue("roles", "error.required");
		}
	}

}
