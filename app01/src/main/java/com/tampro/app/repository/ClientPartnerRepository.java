package com.tampro.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.ClientPartner;

@Repository
public interface ClientPartnerRepository extends PagingAndSortingRepository<ClientPartner, Long>{
	@Query(value = "SELECT * FROM CLIENT_PARTNER CP WHERE  CP.NAME LIKE  ?1",nativeQuery = true)
	public	Page<ClientPartner> findByName(Pageable pageable , String name);
	
	public	Optional<ClientPartner> findByName(String name);
	
	@Override
	public Page<ClientPartner> findAll(Pageable pageable);
	
	public List<ClientPartner> findAll();
}
