package com.tampro.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.Assigned;
import com.tampro.app.repository.AssignedRepository;
import com.tampro.app.service.AssignedService;

@Service
public class AssignedServiceImpl  implements AssignedService{
	@Autowired
	AssignedRepository assignedRepository;

	@Override
	public List<Assigned> getByTask(long taskId) {
		// TODO Auto-generated method stub
		return assignedRepository.findByTask(taskId);
	}

	@Override
	public void save(Assigned assigned) {
		// TODO Auto-generated method stub
		assignedRepository.save(assigned);
	}

	@Override
	public void delete(Assigned assigned) {
		// TODO Auto-generated method stub
		assignedRepository.delete(assigned);
	}

	@Override
	public Assigned findAssignedByTaskAndEmp(long taskId, long employeeId) {
		// TODO Auto-generated method stub
		return assignedRepository.findAssignedByTaskAndEmp(taskId, employeeId);
	}

}
