package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.ProjectManager;

@Repository
public interface ProjectManagerRepository extends CrudRepository<ProjectManager, Long>	{
	
	@Query(value = "SELECT * FROM project_manager pm where pm.project_id = ?1",nativeQuery = true)
	List<ProjectManager> findByProjectId(long projectId);
	@Query(value = "SELECT * FROM project_manager pm where pm.project_id = :projectId and pm.account_id = :accountId ",nativeQuery = true)
	ProjectManager findByProjectAndAccount(@Param("projectId") long projectId,@Param("accountId") long accountId);
}
