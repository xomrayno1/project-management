package com.tampro.app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.app.entity.UserAccount;
import com.tampro.app.service.UserAccountService;
import com.tampro.app.utils.Constant;

@RestController
@RequestMapping("/api/accounts")
public class UserAccountController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@GetMapping(value = "/{id}")
	public UserAccount getUserById(@PathVariable("id") long id) {
		return userAccountService.getAccountById(id);
	}
	@GetMapping(value = "/admin")
	public List<UserAccount> getUserByAdmin() {
		userAccountService.getAllByRole(Constant.ROLE_ADMIN).forEach(item -> {
			item.setPassword(null);
		});
		return userAccountService.getAllByRole(Constant.ROLE_ADMIN);
	}
	@GetMapping
	public List<UserAccount> getByProjects(@RequestParam("projectsId")	long projectId) {
		List<UserAccount> list = userAccountService.getByProject(projectId);
		list.forEach(item -> {
			item.setPassword(null);
		});
		return list;
		 
	}
}
