package com.tampro.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tampro.app.dao.ProjectDAO;
import com.tampro.app.dto.ProjectClient;
import com.tampro.app.entity.ClientPartner;
import com.tampro.app.entity.OnProject;
import com.tampro.app.entity.Project;
import com.tampro.app.entity.ProjectManager;
import com.tampro.app.entity.Task;
import com.tampro.app.entity.UserAccount;
import com.tampro.app.repository.ProjectManagerRepository;
import com.tampro.app.repository.ProjectRepository;
import com.tampro.app.repository.UserAccountRepository;
import com.tampro.app.service.OnProjectService;
import com.tampro.app.service.ProjectService;
import com.tampro.app.service.TaskService;
import com.tampro.app.utils.Paging;

@Service
public class ProjectServiceImpl  implements ProjectService{
	
	@Autowired
	ProjectRepository  proRepo;
	@Autowired
	UserAccountRepository accountRepo;
	@Autowired
	ProjectDAO projectDAO;
	@Autowired
	ProjectManagerRepository projectManaRepo;
	@Autowired
	OnProjectService onProSer;
	@Autowired
	TaskService taskService;
	

	@Override
	public List<Project> getAll(Paging paging, Project project) {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccount account = accountRepo.findByUsername(authentication.getName()).orElseGet(null);
		StringBuilder builder = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(project != null) {
			if(project.getName() != null) {
				builder.append("	and p.name like :name ");
				mapParams.put("name", "%"+project.getName()+"%");
			}
		}	
		return projectDAO.findAllProjectByAccount(mapParams, account.getId(), builder, paging);
	}

	@Override
	public ProjectClient getProjectById(long id) {
		// TODO Auto-generated method stub
		List<OnProject> listOnProject = onProSer.getByClient(id);
		Project project = proRepo.findById(id).orElseGet(null);
		ProjectClient projectClient = null;
		if(project != null) {
			List<Long> listClientId = new ArrayList<Long>();
			for(OnProject pro : listOnProject ) {
				listClientId.add(pro.getClientPartner().getId());
			}
			projectClient = project.convertDTO();
			projectClient.setListClientId(listClientId);
		}
		return projectClient;
	}

	@Override
	public Project getProjectByName(String name) {
		// TODO Auto-generated method stub
		return proRepo.findByName(name).orElse(null);
	}

	@Override
	public void update(ProjectClient projectClient) throws Exception {
		// TODO Auto-generated method stub
		try {
			Project project = projectClient.convertEntity();
			project.setUpdateDate(new Date());
			proRepo.save(project);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public Project add(ProjectClient projectClient) throws Exception {
		// TODO Auto-generated method stub
		try {
			Project project = projectClient.convertEntity();
			project.setCreateDate(new Date());
			project.setUpdateDate(new Date());
			Project newProject = proRepo.save(project);
			for(Long item : projectClient.getListClientId()) {
				onProSer.saveOnProject(new OnProject(newProject,new ClientPartner(item)));
			}
			return newProject;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void delete(ProjectClient projectClient) throws Exception {
		// TODO Auto-generated method stub
		try {
			Project project = projectClient.convertEntity();
			long projectId = project.getId();
			List<ProjectManager> listProManager = projectManaRepo.findByProjectId(projectId);	 
			projectManaRepo.deleteAll(listProManager);// xóa relationship với projectManager
			List<Task> listTask = taskService.getTaskByProjectId(projectId); 
			taskService.deleteAll(listTask); //xóa relationship với project
			List<OnProject> listOnProject = onProSer.getByProject(projectId);
			onProSer.deleteAll(listOnProject);		//xóa relationship với onProject
			proRepo.delete(project);
			 
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

}
