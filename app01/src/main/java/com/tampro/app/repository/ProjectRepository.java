package com.tampro.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
 
 
			
	@Query(value = "SELECT * FROM PROJECT PRO WHERE  PRO.NAME LIKE  ?1",nativeQuery = true)
	public	Page<Project> findByName(Pageable pageable , String name);
	
	public	Optional<Project> findByName(String name); 
	@Override
	public Page<Project> findAll();
}
 
