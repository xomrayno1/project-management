package com.tampro.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tampro.app.dto.MyChart;
import com.tampro.app.entity.Task;
import com.tampro.app.repository.TaskRepository;
import com.tampro.app.service.TaskService;

@Service
public class TaskServiceImpl  implements TaskService{
	@Autowired
	TaskRepository taskRepo;

	@Override
	public Task addTask(Task task) {
		// TODO Auto-generated method stub
		 
		return taskRepo.save(task);
	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		  taskRepo.save(task);
	}

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
		taskRepo.delete(task);
	}

	@Override
	public Task getTaskById(long id) {
		// TODO Auto-generated method stub
		return taskRepo.findById(id).orElse(null);
	}

	@Override
	public List<Task> getTaskByProjectId(long id) {
		// TODO Auto-generated method stub
		return taskRepo.findByProjectId(id);
	}

	@Override
	public Page<Task> getAll(Pageable pageable, Task task) {
		// TODO Auto-generated method stub
		Page<Task>	pageTask = null;
		 
		if(task.getName() != null  ) {
			 pageTask = taskRepo.findByName("%"+task.getName()+"%",pageable);
		}else {			 	
			pageTask = taskRepo.findAll(pageable);
			 
		}
		 return pageTask;
	}

	@Override
	public void deleteAll(List<Task> tasks) {
		// TODO Auto-generated method stub
		taskRepo.deleteAll(tasks);
	}

	@Override
	public List<MyChart> getChart(long projectId) {
		// TODO Auto-generated method stub
		return taskRepo.getChart(projectId);
	}

}
