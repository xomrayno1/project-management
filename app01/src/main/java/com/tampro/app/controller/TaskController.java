package com.tampro.app.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

import com.tampro.app.entity.Assigned;
import com.tampro.app.entity.Employee;
import com.tampro.app.entity.Position;
import com.tampro.app.entity.Project;
import com.tampro.app.entity.Task;
import com.tampro.app.service.AssignedService;
import com.tampro.app.service.EmployeeService;
import com.tampro.app.service.PositionService;
import com.tampro.app.service.ProjectService;
import com.tampro.app.service.TaskService;
import com.tampro.app.utils.Constant;
import com.tampro.app.validator.TaskValidator;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	TaskService taskService;
	@Autowired
	TaskValidator taskValidator;
	@Autowired
	ProjectService projectService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	PositionService positionService;
	@Autowired
	AssignedService assignedService;
	
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == Task.class) {
			dataBinder.addValidators(taskValidator);
		}
	}
	
	@RequestMapping
	public String tasks(@RequestParam(defaultValue = "0",required = false) int page,
							@ModelAttribute("searchForm") Task task
							,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
				Constant.MAX_PAGE_SIZE );
		model.addAttribute("pageTask", taskService.getAll(pageable, task ));
		projectService.getAll(null, null);
		List<Employee>  listEmp =  employeeService.getAll();
		model.addAttribute("listEmp", listEmp);
		return "tasks/task-list";
	}
	@GetMapping("/new")
	public String createTasks(Model map) {
		map.addAttribute("submitForm", new Task());
		initSelect(map);
		return "tasks/task-action";
	}
	@GetMapping("/edit/{id}")
	public String editTasks(Model map,@PathVariable("id") long id) {
		Task task = taskService.getTaskById(id);
		initSelect(map);
		map.addAttribute("submitForm", task);
		List<Assigned> listAss = 	assignedService.getByTask(id);
		map.addAttribute("listAss", listAss);		
		List<Position>	listPo = positionService.getAll(); 
		map.addAttribute("listPo", listPo);
		return "tasks/task-action";
	}
	@GetMapping("/delete/{id}")
	public String deleteTasks(Model map,@PathVariable("id") long id) {
		try {
			Task task = taskService.getTaskById(id);
			if(task != null) {
				taskService.deleteTask(task);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/tasks";
	}
	@PostMapping("/save")
	public String createTasks(Model model,@ModelAttribute("submitForm") @Validated Task task ,BindingResult result) {
		if(result.hasErrors()) {
			initSelect(model);
			return "tasks/task-action";
		}
		if(task.getId() != 0) {
			try {
				taskService.updateTask(task);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				taskService.addTask(task);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/tasks";
	}
	@PostMapping("/assigned/save")
	public String addTaskAssigned(Model model,@RequestParam("taskId") long taskId
			,@RequestParam("empId") List<Long> listId) {
		List<Assigned> listAss = assignedService.getByTask(taskId);
		listAss.forEach(item->{
			boolean flag = true;
			for(long  empId : listId) {
				if(empId == item.getEmployee().getId()) {
					flag = false;
					break;
				} 
			}
			if(flag) {
				Assigned ass  = assignedService.findAssignedByTaskAndEmp(taskId, item.getEmployee().getId());
				assignedService.delete(ass);
			}
		});
		listId.forEach(item->{ // check nếu có rồi thì bỏ , nếu chưa có thì add 
			boolean flag = true;
			for (Assigned assigned : listAss) {
				if(item == assigned.getEmployee().getId()) { //nếu  như đã có rồi thì gán false
					flag = false;
					break;
				}
			}
			if(flag) { // chưa có 
				assignedService.save(new Assigned(new Task(taskId), new Employee(item)));
			}
		});
		  
		return "redirect:/tasks";
	}
	public void initSelect(Model model) {
		List<Project> list = projectService.getAll(null, null);
		Collections.sort(list, new Comparator<Project>() {

			@Override
			public int compare(Project o1, Project o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		model.addAttribute("listProject", list);
	}
}
