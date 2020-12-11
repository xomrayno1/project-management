package com.tampro.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonIgnore
	private Project project;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date plannedStartDate;
	@Temporal(TemporalType.DATE)
	private Date plannedEndDate;
	private BigDecimal plannedBudget;
	private Date actualStartDate;
	private Date actualEndDate;
	private BigDecimal actualBudget;
	private String stages; // NOTSTARTED COMPLETED INPROGRESS
	
	
	
	 
	public String getStages() {
		return stages;
	}
	public void setStages(String stages) {
		this.stages = stages;
	}
	public Task(long id) {
		super();
		this.id = id;
	}
	public Task() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPlannedStartDate() {
		return plannedStartDate;
	}
	public void setPlannedStartDate(Date plannedStartDate) {
		this.plannedStartDate = plannedStartDate;
	}
	public Date getPlannedEndDate() {
		return plannedEndDate;
	}
	public void setPlannedEndDate(Date plannedEndDate) {
		this.plannedEndDate = plannedEndDate;
	}
	public BigDecimal getPlannedBudget() {
		return plannedBudget;
	}
	public void setPlannedBudget(BigDecimal plannedBudget) {
		this.plannedBudget = plannedBudget;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public BigDecimal getActualBudget() {
		return actualBudget;
	}
	public void setActualBudget(BigDecimal actualBudget) {
		this.actualBudget = actualBudget;
	}
	
	
	public boolean getStage() {
		if(actualEndDate != null) {
			return true;
		}
		return false;
	}
	
	
}
