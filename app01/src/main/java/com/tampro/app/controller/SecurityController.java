package com.tampro.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
//https://o7planning.org/vi/11705/tao-ung-dung-login-voi-spring-boot-spring-security-jpa
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {
	

	 @GetMapping("/logout")
	 public String logout(HttpServletRequest request , HttpServletResponse response) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if(authentication != null) {
			 new SecurityContextLogoutHandler().logout(request,response,authentication);
		 }
		 return "redirect:/login";
	 }
	 @GetMapping("/login")
	 public String login() {
		 return "login";
	 }
	 
}
