package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.OnProject;

@Repository
public interface OnProjectRepository extends CrudRepository<OnProject, Long>{
	
	@Query(value = " select * from on_project op where op.project_id = ?1 ",  nativeQuery = true)
	List<OnProject> findByProject(long projectId);

	
	@Query(value = " select * from on_project op where op.client_id = ?1 ",  nativeQuery = true)
	List<OnProject> findByClient(long clientId);
}
