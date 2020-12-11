package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.entity.ClientPartner;
import com.tampro.app.utils.ModelMapper;

public interface ClientPartnerService {

	Page<ClientPartner> getAll(Pageable pageable, ClientPartner clientPartner);
	
	List<ClientPartner> getAll();
	
	ClientPartner getClientPartnerById(long id);

	void delete(ClientPartner clientPartner) throws Exception;

	void add(ClientPartner clientPartner)	throws Exception;

	void update(ClientPartner clientPartner)	throws Exception;

}
