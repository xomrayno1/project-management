package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.dto.MyChart;
import com.tampro.app.entity.Task;

public interface TaskService {
	
	public Task addTask(Task task);
	
	public void updateTask(Task task);
	
	public void deleteTask(Task task);
	
	public void deleteAll(List<Task> tasks);
	
	Task getTaskById(long id);
	
	List<Task> getTaskByProjectId(long id);
	
	public Page<Task> getAll(Pageable pageable ,Task task );
	
	List<MyChart> getChart(long projectId);
	
}
