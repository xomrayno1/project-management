package com.tampro.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.ClientPartner;
import com.tampro.app.repository.ClientPartnerRepository;
import com.tampro.app.service.ClientPartnerService;

@Service
public class ClientPartnerServiceImpl implements ClientPartnerService{
	@Autowired
	ClientPartnerRepository cpRepo;

	@Override
	public Page<ClientPartner> getAll(Pageable pageable, ClientPartner clientPartner) {
		// TODO Auto-generated method stub
		Page<ClientPartner>	pageClient = null;
		 
		if(clientPartner.getName() != null  ) {
			pageClient = cpRepo.findByName(pageable, "%"+clientPartner.getName()+"%");
		}else {			 	
			pageClient = cpRepo.findAll(pageable);	  
		}
		 return pageClient;
	}

	@Override
	public ClientPartner getClientPartnerById(long id) {
		// TODO Auto-generated method stub
		return cpRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(ClientPartner clientPartner) throws Exception {
		// TODO Auto-generated method stub
		try {
			cpRepo.delete(clientPartner);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void add(ClientPartner clientPartner) throws Exception {
		// TODO Auto-generated method stub
		try {
			clientPartner.setUpdateDate(new Date());
			clientPartner.setCreateDate(new Date());
			cpRepo.save(clientPartner);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void update(ClientPartner clientPartner) throws Exception {
		// TODO Auto-generated method stub
		try {
			clientPartner.setUpdateDate(new Date());
			cpRepo.save(clientPartner);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public List<ClientPartner> getAll() {
		// TODO Auto-generated method stub
		return cpRepo.findAll();
	}

}
