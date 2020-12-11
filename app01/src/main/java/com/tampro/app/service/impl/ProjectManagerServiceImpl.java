package com.tampro.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.ProjectManager;
import com.tampro.app.repository.ProjectManagerRepository;
import com.tampro.app.service.ProjectManagerService;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{
	
	@Autowired
	ProjectManagerRepository pmRepo;

	@Override
	public void saveProjectManager(ProjectManager projectManager) throws Exception {
		// TODO Auto-generated method stub
		try {
			pmRepo.save(projectManager);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public List<ProjectManager> getProjectByProjectId(long projectId) {
		// TODO Auto-generated method stub
		return pmRepo.findByProjectId(projectId);
	}

	@Override
	public ProjectManager getByProjectAndAccount(long projectId, long accountId) {
		// TODO Auto-generated method stub
		return pmRepo.findByProjectAndAccount(projectId, accountId);
	}

	@Override
	public void deleteProjectManager(ProjectManager projectManager) throws Exception {
		// TODO Auto-generated method stub
		pmRepo.delete(projectManager);
	}

}
