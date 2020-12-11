package com.tampro.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.UserAccount;
import com.tampro.app.repository.UserAccountRepository;
import com.tampro.app.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	UserAccountRepository accountRepo;

	@Override
	public Page<UserAccount> getAll(Pageable pageable, UserAccount userAccount) {
		// TODO Auto-generated method stub
		Page<UserAccount>	pageEmp = null;
		 
		if(userAccount.getUsername() != null  ) {
			 pageEmp = accountRepo.findByUsername(pageable, "%"+userAccount.getUsername()+"%");
			 
 
		}else {			 	
			 pageEmp = accountRepo.findAll(pageable);
			 
		}
		 return pageEmp;
	}

	 

	@Override
	public void delete(UserAccount userAccount) throws Exception {
		// TODO Auto-generated method stub
		try {
			 
			 
			accountRepo.save(userAccount);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void update(UserAccount userAccount) throws Exception {
		// TODO Auto-generated method stub
		try {
			userAccount.setUpdateDate(new Date());
			 
			accountRepo.save(userAccount);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void add(UserAccount userAccount) throws Exception {
		// TODO Auto-generated method stub
		try {
			userAccount.setUpdateDate(new Date());
			userAccount.setCreateDate(new Date());
			accountRepo.save(userAccount);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public UserAccount findByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepo.findByEmail(email).orElse(null);
	}

	@Override
	public UserAccount getAccountById(long id) {
		// TODO Auto-generated method stub
	 
		return accountRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Account not found with id "+id));
	}



	@Override
	public UserAccount getByUsername(String username) {
		// TODO Auto-generated method stub
		return accountRepo.findByUsername(username).orElse(null);
	}



	@Override
	public List<UserAccount> getAllByRole(long roleId) {
		// TODO Auto-generated method stub
		return accountRepo.findByRole(roleId);
	}



	@Override
	public List<UserAccount> getByProject(long projectId) {
		// TODO Auto-generated method stub
		return accountRepo.findByProject(projectId);
	}

}
