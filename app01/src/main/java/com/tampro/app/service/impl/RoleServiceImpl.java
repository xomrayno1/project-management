package com.tampro.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.Role;
import com.tampro.app.repository.RoleRepository;
import com.tampro.app.service.RoleService;

@Service
public class RoleServiceImpl  implements RoleService{
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

}
