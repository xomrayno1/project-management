package com.tampro.app.dto;

import java.util.Date;
import java.util.List;

import com.tampro.app.entity.Project;

public class ProjectClient {	 
	private long id;
	private String name;	 
	private Date plannedStartDate;	 
	private Date plannedEndDate; 
	private Date actualStartDate; 
	private Date actualEndDate;
	private String projectDescription; 
	private Date createDate;
	private Date updateDate;
	private String stage; // NOTSTARTED COMPLETED INPROGRESS
	private List<Long> listClientId;
	
	
	
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
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public List<Long> getListClientId() {
		return listClientId;
	}
	public void setListClientId(List<Long> listClientId) {
		this.listClientId = listClientId;
	}
	
	public Project convertEntity() {
		Project project = new Project();
		project.setActualEndDate(actualEndDate);
		project.setActualStartDate(actualStartDate);
		project.setCreateDate(createDate);
		project.setId(id);
		 
		project.setName(name);
		project.setPlannedEndDate(plannedEndDate);
		project.setPlannedStartDate(plannedStartDate);
		project.setProjectDescription(projectDescription);
		project.setStage(stage);
		project.setUpdateDate(updateDate);
		return project;
	}
	
}
