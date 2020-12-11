package com.tampro.app.service;

import java.util.List;

import com.tampro.app.entity.ProjectManager;

public interface ProjectManagerService {
	
	public void saveProjectManager(ProjectManager projectManager) throws Exception;
	
	public void deleteProjectManager(ProjectManager projectManager) throws Exception;
	
	List<ProjectManager> getProjectByProjectId(long projectId);
	
	ProjectManager getByProjectAndAccount(long projectId, long accountId);
}
