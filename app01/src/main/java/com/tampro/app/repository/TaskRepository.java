package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.app.dto.MyChart;
import com.tampro.app.entity.Task;

@Repository
public interface TaskRepository  extends CrudRepository<Task, Long>{

	
	 	@Query(value = "Select * from task t where t.project_id = ?1" , nativeQuery = true)
		List<Task> findByProjectId(long projectId);
	 	
	 	Page<Task> findAll(Pageable pageable);
	 	@Query(value = "SELECT * FROM TASK T WHERE  T.NAME LIKE  ?1",nativeQuery = true)

	 	Page<Task> findByName(String name, Pageable pageable);
	 	
	 	
	 	@Query(value = " SELECT count(*) as count, stages as label FROM task  where project_id = 1  group by stages " , nativeQuery = true)
		List<MyChart> getChart(long projectId);
}
