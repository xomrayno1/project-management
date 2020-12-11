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

import com.tampro.app.entity.ClientPartner;
import com.tampro.app.service.ClientPartnerService;
import com.tampro.app.utils.Constant;

@Controller
@RequestMapping("/partners")
public class ClientPartnerController {
	@Autowired
	ClientPartnerService clientPartnerService;
	
	
	@InitBinder
	public void innitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
		
	}
	@RequestMapping
	public String partners(@RequestParam(defaultValue = "0",required = false) int page,
			@ModelAttribute("searchForm") ClientPartner clientPartner
			,Model model) {
		Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1,
		Constant.MAX_PAGE_SIZE );
		model.addAttribute("pagePart", clientPartnerService.getAll(pageable, clientPartner ));
		return "partners/partners-list";
	}
	
	@GetMapping("/new")
	public String createPartners( Model model) {
		model.addAttribute("submitForm", new ClientPartner()); 
		return "partners/partners-action";
	}

	@GetMapping("/edit/{id}")
	public String editPartners(Model map,@PathVariable("id") long id) {
		ClientPartner clientPartner = clientPartnerService.getClientPartnerById(id);
		map.addAttribute("submitForm", clientPartner);
		return "partners/partners-action";
	}
	@GetMapping("/delete/{id}")
	public String deletePartners(Model map,@PathVariable("id") long id) {
		try {
			ClientPartner clientPartner = clientPartnerService.getClientPartnerById(id);
			if(clientPartner != null) {
				clientPartnerService.delete(clientPartner);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/partners";
	}
	@PostMapping("/save")
	public String createPartners(@ModelAttribute("submitForm") @Validated ClientPartner clientPartner,BindingResult result) {
		if(result.hasErrors()) {
			return "partners/partners-action";
		}
		if(clientPartner.getId() != 0) {
			try {
				clientPartnerService.update(clientPartner);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				clientPartnerService.add(clientPartner);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/partners";
	}
}
