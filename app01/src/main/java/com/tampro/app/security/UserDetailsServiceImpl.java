package com.tampro.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.Role;
import com.tampro.app.entity.UserAccount;
import com.tampro.app.repository.UserAccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserAccountRepository userAccountRepository;
	@Autowired
	BCryptPasswordEncoder	bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountRepository.findByUsername(username).orElse(null);
		if(userAccount == null) {
			System.out.println("username not found "+username);
			throw new  UsernameNotFoundException("username not found "+username);
		}
		System.out.println(" found "+username);
		List<Role> listRole  = userAccount.getRoles();
		for (Role role : listRole) {
			System.out.println(role);
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(listRole != null) {
			for (Role role : listRole) {
				 GrantedAuthority  grantedAuthority  = new SimpleGrantedAuthority(role.getName());
				 authorities.add(grantedAuthority);
			}
		}
		UserDetails userDetails = (UserDetails) new User(userAccount.getUsername()
				,userAccount.getPassword(),authorities);
		
		return userDetails;
	}

}
