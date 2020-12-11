package com.tampro.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.app.entity.Team;
import com.tampro.app.service.TeamService;
import com.tampro.app.utils.Constant;
import com.tampro.app.validator.TeamValidator;

@Controller
@RequestMapping("/teams")
public class TeamController {
	@Autowired
	TeamService teamService;
	@Autowired
	TeamValidator teamValidator;
 
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == Team.class) {
			dataBinder.addValidators(teamValidator);
		}
	}
	
	@RequestMapping
	public String teams(@RequestParam(defaultValue = "0",required = false) int page,
							@ModelAttribute("searchForm") Team team
							,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
				Constant.MAX_PAGE_SIZE );
		model.addAttribute("pageTeam", teamService.getAll(pageable, team ));
		 
		return "teams/team-list";
	}
	@GetMapping("/new")
	public String createTeam(Model map) {
		map.addAttribute("submitForm", new Team());
		return "teams/team-action";
	}
	@GetMapping("/edit/{id}")
	public String editTeam(Model map,@PathVariable("id") long id) {
		Team team = teamService.getTeamById(id);		 
		map.addAttribute("submitForm", team);
		return "teams/team-action";
	}
	@GetMapping("/delete/{id}")
	public String deleteTeam(Model map,@PathVariable("id") long id) {
		try {
			Team team = teamService.getTeamById(id);
			if(team != null) {
				teamService.deleteTeam(team);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/teams";
	}
	@PostMapping("/save")
	public String createTeam(Model model,@ModelAttribute("submitForm") @Validated Team team ,BindingResult result) {
		if(result.hasErrors()) {
			return "teams/team-action";
		}
		if(team.getId() != 0) {
			try {
				teamService.updateTeam(team);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				teamService.addTeam(team);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/teams";
	}
	 
	 
 
}
