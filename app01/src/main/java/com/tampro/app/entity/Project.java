package com.tampro.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tampro.app.dto.ProjectClient;
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date plannedStartDate;
	@Temporal(TemporalType.DATE)
	private Date plannedEndDate;
	@Temporal(TemporalType.DATE)
	private Date actualStartDate;
	@Temporal(TemporalType.DATE)
	private Date actualEndDate;
	private String projectDescription;
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	private Date updateDate;
	private String stage; // NOTSTARTED COMPLETED INPROGRESS
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},mappedBy = "project")
	private List<OnProject> listOnProject;
 
	
	public Project() {
		 
	}
	public Project(long id) {
		 
		this.id = id;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
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
	public List<OnProject> getListOnProject() {
		return listOnProject;
	}
	public void setListOnProject(List<OnProject> listOnProject) {
		this.listOnProject = listOnProject;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", plannedStartDate=" + plannedStartDate + ", plannedEndDate="
				+ plannedEndDate + ", actualStartDate=" + actualStartDate + ", actualEndDate=" + actualEndDate
				+ ", projectDescription=" + projectDescription + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", stage=" + stage + ", listOnProject=" + listOnProject + "]";
	}
	 
	 
	public ProjectClient convertDTO() {
		ProjectClient projectClient = new ProjectClient();
		projectClient.setActualEndDate(actualEndDate);
		projectClient.setActualStartDate(actualStartDate);
		projectClient.setCreateDate(createDate);
		projectClient.setId(id);
		 
		projectClient.setName(name);
		projectClient.setPlannedEndDate(plannedEndDate);
		projectClient.setPlannedStartDate(plannedStartDate);
		projectClient.setProjectDescription(projectDescription);
		projectClient.setStage(stage);
		projectClient.setUpdateDate(updateDate);
		return projectClient;
	}
	 
	 
	
	
	
}
