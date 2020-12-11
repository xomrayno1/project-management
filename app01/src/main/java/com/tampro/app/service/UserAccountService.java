package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.entity.UserAccount;

public interface UserAccountService {

	Page<UserAccount> getAll(Pageable pageable, UserAccount userAccount);
	
	List<UserAccount> getAllByRole(long roleId);
	
	public List<UserAccount> getByProject(long projectId);
	
	UserAccount	findByEmail(String email);

	void delete(UserAccount userAccount) throws Exception;

	void update(UserAccount userAccount) throws Exception;

	void add(UserAccount userAccount) throws Exception;

	UserAccount getAccountById(long id);
	
	UserAccount getByUsername(String username);
	

}
