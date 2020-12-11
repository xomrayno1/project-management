package com.tampro.app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.app.entity.Task;
import com.tampro.app.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskApiController {
	@Autowired
	TaskService taskService;
	
	@GetMapping(value = "/{id}")
	public Task getTaskById(@PathVariable long id) {
		
		return taskService.getTaskById(id);
	}
	
	
	@GetMapping 
	public ResponseEntity<List<Task>> getTaskByProject(@RequestParam("projectId") long projectId) {
		
		try {
			List<Task> listTask =  taskService.getTaskByProjectId(projectId);
			return new ResponseEntity<List<Task>>(listTask,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
