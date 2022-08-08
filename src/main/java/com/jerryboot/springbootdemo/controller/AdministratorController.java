package com.jerryboot.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerryboot.springbootdemo.dao.AdministratorDao;
import com.jerryboot.springbootdemo.model.Administrator;
import com.jerryboot.springbootdemo.service.AdministratorService;

@Controller
public class AdministratorController {
	@Autowired
	private AdministratorDao dao;
	@Autowired
	private AdministratorService service;
	

	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}
					,value ="/Backstage/adminLogin")
	public String goLogin() {
		return "Backstage/jsp/login";
	}
	

	
	
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}
					,value="/Backstage/loginSuccess")
	public String loginOK() {
		return "Backstage/jsp/loginOK";
	}
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}
					,value="/Backstage/loginFail")
	public String loginError() {
		return "Backstage/jsp/loginError";
	}

	
	
	
	@GetMapping("/goEnroll")
	public String enrollAdmin(Model model) {
		model.addAttribute("adminBean",new Administrator());
		return "Backstage/jsp/enrollAdminPage";
	}
	
	@PostMapping("/enroll")
	@ResponseBody
	public String enrollForm(@ModelAttribute (name="adminBean")Administrator enrollAdmin) {
		
//		String encodedPassword = passwordEncoder.encode(enrollAdmin.getAdminPassword().trim());
//		enrollAdmin.setAdminPassword(encodedPassword);
		service.insertAdmin(enrollAdmin);
		return "Enroll Success!";
	}

	
	
	

}
