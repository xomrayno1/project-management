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

import com.tampro.app.entity.Position;
import com.tampro.app.service.PositionService;
import com.tampro.app.utils.Constant;
import com.tampro.app.validator.PositionValidator;

@Controller
@RequestMapping("/positions")
public class PositionController {
	@Autowired
	PositionService positionService;
	@Autowired
	PositionValidator positionValidator;
 
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == Position.class) {
			dataBinder.addValidators(positionValidator);
		}
	}
	
	@RequestMapping
	public String positions(@RequestParam(defaultValue = "0",required = false) int page,
							@ModelAttribute("searchForm") Position position
							,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
				Constant.MAX_PAGE_SIZE );
		model.addAttribute("pagePosi", positionService.getAll(pageable, position ));
		return "positions/position-list";
	}
	@GetMapping("/new")
	public String createPosition(Model map) {
		map.addAttribute("submitForm", new Position());
		return "positions/position-action";
	}
	@GetMapping("/edit/{id}")
	public String editPosition(Model map,@PathVariable("id") long id) {
		Position position = positionService.getPositionById(id);		 
		map.addAttribute("submitForm", position);
		return "positions/position-action";
	}
	@GetMapping("/delete/{id}")
	public String deletePosition(Model map,@PathVariable("id") long id) {
		try {
			Position position = positionService.getPositionById(id);
			if(position != null) {
				positionService.deletePosition(position);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/positions";
	}
	@PostMapping("/save")
	public String createPosition(Model model,@ModelAttribute("submitForm") @Validated Position position ,BindingResult result) {
		if(result.hasErrors()) {
			return "positions/position-action";
		}
		if(position.getId() != 0) {
			try {
				positionService.updatePosition(position);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				positionService.addPosition(position);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/positions";
	}
	 
}
