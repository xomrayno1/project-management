package com.tampro.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.OnProject;
import com.tampro.app.repository.OnProjectRepository;
import com.tampro.app.service.OnProjectService;

@Service
public class OnProjectServiceImpl  implements OnProjectService{
	@Autowired
	OnProjectRepository onProjectRepo;

	@Override
	public void saveOnProject(OnProject onProject) throws Exception {
		// TODO Auto-generated method stub
		try {
			onProjectRepo.save(onProject);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public void deleteOnProject(OnProject onProject) throws Exception {
		// TODO Auto-generated method stub
		try {
			onProjectRepo.delete(onProject);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
	}

	@Override
	public List<OnProject> getByClient(long clientId) {
		// TODO Auto-generated method stub
		return onProjectRepo.findByClient(clientId);
	}

	@Override
	public void deleteAll(List<OnProject> list) {
		// TODO Auto-generated method stub
		onProjectRepo.deleteAll(list);
	}

	@Override
	public List<OnProject> getByProject(long projectId) {
		// TODO Auto-generated method stub
		return onProjectRepo.findByProject(projectId);
	}

}
