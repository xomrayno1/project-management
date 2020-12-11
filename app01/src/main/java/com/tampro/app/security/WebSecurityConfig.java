package com.tampro.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	 
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/");		 
		 http.authorizeRequests().and().formLogin()//
         // Submit URL của trang login
         .loginProcessingUrl("/processLogin") // Submit URL
         .loginPage("/login")//
         .defaultSuccessUrl("/")//
         .failureUrl("/login?error=true")//
         .usernameParameter("username")//
         .passwordParameter("password").and().authorizeRequests()
			.antMatchers("/projects/**").hasRole("ADMIN") // ROLE_ADMIN
			//.antMatchers("/employees/new").hasAuthority("ADMIN") // ADMIN
			.antMatchers("/employees**").hasRole("ADMIN")
			.antMatchers("/accounts/**").hasRole("SUPERADMIN")
			.antMatchers("/partners/**").hasRole("ADMIN")
			.antMatchers("/tasks/**").hasRole("ADMIN")
			.antMatchers("/","/index").authenticated()
			.antMatchers("/logout","/login").permitAll();
		 

		
	}
	 @Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		 web.ignoring().antMatchers("/static/**");
	}
}
