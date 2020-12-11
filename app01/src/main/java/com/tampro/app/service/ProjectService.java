package com.tampro.app.service;

import java.util.List;

import com.tampro.app.dto.ProjectClient;
import com.tampro.app.entity.Project;
import com.tampro.app.utils.Paging;

public interface ProjectService {

	List<Project> getAll(Paging paging, Project project);

	ProjectClient getProjectById(long id);

	Project getProjectByName(String name);
	
	void update(ProjectClient projectClient) throws Exception;

	Project add(ProjectClient projectClient) throws Exception;

	void delete(ProjectClient projectClient) throws Exception;
}
