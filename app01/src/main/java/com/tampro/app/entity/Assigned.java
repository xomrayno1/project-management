package com.tampro.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Assigned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "taskId")
	private Task task;
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	 
	
	
	
	
	
	public Assigned() {
		super();
	}
	
	public Assigned(Task task, Employee employee) {
		 
		this.task = task;
		this.employee = employee;
	}
 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	 
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	 
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Assigned [id=" + id + ", task=" + task + ", employee=" + employee +      "]";
	}

	
	
}
