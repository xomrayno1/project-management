package com.tampro.app.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.app.entity.Assigned;
import com.tampro.app.service.AssignedService;
@RestController
@RequestMapping("/api/assigned")
public class AssignedApiController {
	@Autowired
	AssignedService assignedService;
	
	@GetMapping
	public ResponseEntity<List<Assigned>> assignedByTask(@RequestParam("taskId") long taskId){		 
		try {
			 	
			List<Assigned>  response =  assignedService.getByTask(taskId);
			return new ResponseEntity<List<Assigned>>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
