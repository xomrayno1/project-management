package com.tampro.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class OnProject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	@ManyToOne
	@JoinColumn(name = "clientId")
	private ClientPartner clientPartner;
	private Date dateStart;
	private Date dateEnd;
	private String description;
	private boolean isClient;
	private boolean isPartner;
	
	
	
	
	public OnProject() {
		super();
	}
	public OnProject(Project project, ClientPartner clientPartner) {
		super();
		this.project = project;
		this.clientPartner = clientPartner;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ClientPartner getClientPartner() {
		return clientPartner;
	}
	public void setClientPartner(ClientPartner clientPartner) {
		this.clientPartner = clientPartner;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isClient() {
		return isClient;
	}
	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	public boolean isPartner() {
		return isPartner;
	}
	public void setPartner(boolean isPartner) {
		this.isPartner = isPartner;
	}

	
	
	
	
	
}
