package com.tampro.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tampro.app.entity.Team;
import com.tampro.app.repository.TeamRepository;
import com.tampro.app.service.TeamService;

@Service
public class TeamServiceImpl  implements TeamService{

	@Autowired
	TeamRepository teamRepo;
	
	@Override
	public void addTeam(Team team) throws Exception {
		// TODO Auto-generated method stub
		team.setUpdateDate(new Date());
		team.setCreateDate(new Date());
		teamRepo.save(team);
	}

	@Override
	public void updateTeam(Team team) throws Exception {
		// TODO Auto-generated method stub
		team.setUpdateDate(new Date());
		teamRepo.save(team);
	}

	@Override
	public void deleteTeam(Team team) throws Exception {
		// TODO Auto-generated method stub
		teamRepo.delete(team);
	}

	@Override
	public Team getTeamById(long id) {
		// TODO Auto-generated method stub
		return teamRepo.findById(id).orElse(null);
	}

	@Override
	public Page<Team> getAll(Pageable pageable, Team team) {
		// TODO Auto-generated method stub
		Page<Team>	pageTeam = null;
		 
		if(team.getName() != null  ) {
			pageTeam = teamRepo.findByName("%"+team.getName()+"%",pageable);
		}else {			 	
			pageTeam = teamRepo.findAll(pageable);
			 
		}
		 return pageTeam;
	}

	@Override
	public List<Team> getAll() {
		// TODO Auto-generated method stub
		return teamRepo.findAll();
	}

}
