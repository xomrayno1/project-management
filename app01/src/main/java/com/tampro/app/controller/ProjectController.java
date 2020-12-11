package com.tampro.app.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tampro.app.dto.EmployeeTask;
import com.tampro.app.dto.MyChart;
import com.tampro.app.dto.ProjectClient;
import com.tampro.app.entity.ClientPartner;
import com.tampro.app.entity.Employee;
import com.tampro.app.entity.Project;
import com.tampro.app.entity.ProjectManager;
import com.tampro.app.entity.Task;
import com.tampro.app.entity.UserAccount;
import com.tampro.app.repository.TaskRepository;
import com.tampro.app.service.ClientPartnerService;
import com.tampro.app.service.EmployeeService;
import com.tampro.app.service.ProjectManagerService;
import com.tampro.app.service.ProjectService;
import com.tampro.app.service.TaskService;
import com.tampro.app.service.UserAccountService;
import com.tampro.app.utils.Constant;
import com.tampro.app.utils.Paging;
import com.tampro.app.validator.ProjectValidator;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectValidator projectValidator;
	@Autowired
	ClientPartnerService clientParnersService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	ProjectManagerService  projectMService;
	@Autowired
	EmployeeService empService;
	 
	@Autowired
	TaskService taskService;
	
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == Project.class) {
			dataBinder.addValidators(projectValidator);
		}
	}
	@RequestMapping
	public String projects(@RequestParam(defaultValue = "1",required = false) int page,
			@ModelAttribute("searchForm") Project project
			,Model model) {
		Paging  paging = new Paging(Constant.MAX_PAGE_SIZE);
		paging.setIndexPage(page);
		List<Project> list = projectService.getAll(paging, project);
		model.addAttribute("listProject", list);
		model.addAttribute("paging", paging);
		List<UserAccount> listUserRoleAdmin = userAccountService.getAllByRole(Constant.ROLE_ADMIN);
		model.addAttribute("listUserRoleAdmin", listUserRoleAdmin); 
		 
		return "projects/project-list";
	}
	
	@GetMapping("/new")
	public String createProjects(Model map) {
		map.addAttribute("submitForm", new ProjectClient());
		List<ClientPartner> clientPartner	= clientParnersService.getAll();
		map.addAttribute("listClientPartner", clientPartner);
		List<ClientPartner> listClientPartner = clientParnersService.getAll();
		Collections.sort(listClientPartner, new Comparator<ClientPartner>() {

			@Override
			public int compare(ClientPartner o1, ClientPartner o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		map.addAttribute("listClientPartner", listClientPartner);
		
		return "projects/project-action";
	}
	
	 
	@GetMapping("/edit/{id}")
	public String editProjects(Model map,@PathVariable("id") long id) {
		ProjectClient projectClient = projectService.getProjectById(id);
		map.addAttribute("submitForm", projectClient);
		List<ClientPartner> clientPartner	= clientParnersService.getAll();
		map.addAttribute("listClientPartner", clientPartner);
		return "projects/project-action";
	}
	@GetMapping("/delete/{id}")
	public String deleteProject(Model map,@PathVariable("id") long id) {
		try {
			ProjectClient projectClient = projectService.getProjectById(id);
			if(projectClient != null) {
				projectService.delete(projectClient);
				 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/projects";
	}
	@PostMapping("/save")
	public String createProjects(ModelMap map,@ModelAttribute("submitForm") @Validated ProjectClient projectClient,BindingResult result) {
		
		System.out.println(projectClient);
		
		if(result.hasErrors()) {
			List<ClientPartner> clientPartner	= clientParnersService.getAll();
			map.addAttribute("listClientPartner", clientPartner);
			return "projects/project-action";
		}
		if(projectClient.getId() != 0) {
			try {
				projectService.update(projectClient);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				Authentication account = SecurityContextHolder.getContext().getAuthentication();
				UserAccount userAccount = userAccountService.getByUsername(account.getName());
				Project  newProject = projectService.add(projectClient);
				projectMService.saveProjectManager(new ProjectManager(newProject, userAccount));			 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/projects";
	}
	@GetMapping("/manager/save")
	public String saveManager(Model map,@RequestParam("userId") List<Long> listId
			,@RequestParam("proId") long projectId) { 
		List<ProjectManager> listProjectManager = projectMService.getProjectByProjectId(projectId);
		 
		listProjectManager.forEach(item->{ // trong database chua co user // trong database co user
			boolean check = true;
			for(Long ite : listId) {
				if(item.getAccount().getId() == ite) {
					check=false;
					break;
				}
			}
			if(check) {
				//delete
				try {
					System.out.println("controller : delete");
					ProjectManager projectManager = projectMService.getByProjectAndAccount(projectId, item.getAccount().getId());
					projectMService.deleteProjectManager(projectManager);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		for(Long ite : listId) {
			boolean check = true;
			for(ProjectManager item :	listProjectManager) {
				if(ite == item.getAccount().getId()) {
					check = false;
					break;
				}
			}
			if(check) {
				//save
				try {
					System.out.println("controller : save");
					ProjectManager projectManager = 
							new ProjectManager(new Project(projectId),new UserAccount(ite));
					projectMService.saveProjectManager(projectManager);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return "redirect:/projects";
	}
	@GetMapping("/task")
	public String taskProjects(Model map,@RequestParam("id") long id) throws JsonProcessingException {
		ProjectClient projectClient = projectService.getProjectById(id);
		map.addAttribute("project", projectClient);
		Task task = new Task();
		task.setProject(new Project(id));
		map.addAttribute("task", task);
		
		List<Task> listTask = taskService.getTaskByProjectId(id);
		map.addAttribute("listTask", listTask);
		List<EmployeeTask> listEmpTask = empService.employeeTasksByProjectId(id);
		map.addAttribute("listEmpTask", listEmpTask);
		
		List<MyChart> listChart = taskService.getChart(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(listChart);
		System.out.println(jsonString);
		map.addAttribute("chartJson", jsonString);
		
		return "projects/task-project";
	}
	@PostMapping("/task/save")
	public String saveTaskProjects(Model map,@ModelAttribute("task") Task task) {
		taskService.addTask(task);
		return "redirect:/projects";
	}
}
