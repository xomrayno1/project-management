package com.tampro.app.service;

import java.util.List;

import com.tampro.app.entity.OnProject;

public interface OnProjectService {
	
	public void saveOnProject(OnProject onProject) throws Exception;

	public void deleteOnProject(OnProject onProject) throws Exception;
	
	public void deleteAll(List<OnProject> list);
	
	List<OnProject> getByClient(long clientId);
	
	List<OnProject> getByProject(long projectId);
}
