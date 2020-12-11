package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
	Page<Team> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM TEAM T WHERE  T.NAME LIKE  ?1",nativeQuery = true)
 	Page<Team> findByName(String name, Pageable pageable);
	
	
	List<Team> findAll();
}
