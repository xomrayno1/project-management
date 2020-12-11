package com.tampro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tampro.app.entity.Assigned;

@Repository
public interface AssignedRepository extends CrudRepository<Assigned, Long>{

	
	@Query(value = "SELECT * FROM ASSIGNED A WHERE A.task_id = ?1 ",nativeQuery = true)
	List<Assigned> findByTask(long taskId);
	
	@Query(value = "SELECT * FROM ASSIGNED A WHERE A.task_id = :taskId and A.employee_id = :employeeId  ",nativeQuery = true)
	Assigned findAssignedByTaskAndEmp(@Param("taskId") long taskId,@Param("employeeId") long employeeId);
}
