package com.tampro.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.tampro.app.entity.UserAccount;
import com.tampro.app.service.RoleService;
import com.tampro.app.service.UserAccountService;
import com.tampro.app.utils.Constant;
import com.tampro.app.validator.UserAccountValidator;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	UserAccountValidator userAccountValidator;
	@Autowired
	RoleService roleSer;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == UserAccount.class) {
			dataBinder.addValidators(userAccountValidator);
		}
	}
	
	@RequestMapping
	public String accounts(@RequestParam(defaultValue = "0",required = false) int page,
							@ModelAttribute("searchForm") UserAccount userAccount
							,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
				Constant.MAX_PAGE_SIZE );
		model.addAttribute("pageUser", userAccountService.getAll(pageable, userAccount ));
		
		return "accounts/account-list";
	}
	@GetMapping("/new")
	public String createAccount(Model map) {
		map.addAttribute("submitForm", new UserAccount());
		map.addAttribute("listRole", roleSer.getAll());
		return "accounts/account-action";
	}
	@GetMapping("/edit/{id}")
	public String editAccount(Model map,@PathVariable("id") long id) {
		UserAccount userAccount = userAccountService.getAccountById(id);
		 
		map.addAttribute("submitForm", userAccount);
		map.addAttribute("listRole", roleSer.getAll());
		return "accounts/account-action";
	}
	@GetMapping("/delete/{id}")
	public String deleteAccount(Model map,@PathVariable("id") long id) {
		try {
			UserAccount userAccount = userAccountService.getAccountById(id);
			if(userAccount != null) {
				userAccountService.delete(userAccount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/accounts";
	}
	@PostMapping("/save")
	public String createEmployee(Model map,@ModelAttribute("submitForm") @Validated UserAccount userAccount,BindingResult result) {
		map.addAttribute("listRole", roleSer.getAll());
		if(result.hasErrors()) {
			return "accounts/account-action";
		}
		System.out.println(userAccount.getRoles());
		 
		if(userAccount.getId() != 0) {
			try {
				UserAccount account = userAccountService.getAccountById(userAccount.getId());
				if(userAccount.getPassword().equals(account.getPassword())) {
					userAccountService.update(userAccount);
				}else{
					userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
					userAccountService.update(userAccount);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
				userAccountService.add(userAccount);
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/accounts";
	}
}
