package com.tampro.app.service;

import java.util.List;

import com.tampro.app.entity.Assigned;

public interface AssignedService {
	
	List<Assigned> getByTask(long taskId);
	
	
	public void save(Assigned assigned);
	
	public void delete(Assigned assigned);

	
	Assigned findAssignedByTaskAndEmp(long taskId,long employeeId);
}
