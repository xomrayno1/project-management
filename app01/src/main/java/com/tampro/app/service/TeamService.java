package com.tampro.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tampro.app.entity.Team;

public interface TeamService {

	void addTeam(Team team) throws Exception;

	void updateTeam(Team team)	throws Exception;

	void deleteTeam(Team team)	throws Exception;

	Team getTeamById(long id);

	Page<Team> getAll(Pageable pageable, Team team);

	List<Team> getAll();
}
