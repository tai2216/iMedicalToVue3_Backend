package com.jerryboot.springbootdemo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jerryboot.springbootdemo.dao.AssistiveDeviceApplicationFormDao;
import com.jerryboot.springbootdemo.dao.AssistiveDeviceDao;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.model.AssistiveDevice;
import com.jerryboot.springbootdemo.model.AssistiveDeviceApplicationForm;
import com.jerryboot.springbootdemo.model.EditLog;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.service.AssistiveDeviceApplicationFormService;
import com.jerryboot.springbootdemo.service.AssistiveDeviceService;

@Controller
public class AssistiveDeviceApplicationFormController {

	@Autowired
	private AssistiveDeviceApplicationFormService service;
	@Autowired
	private AssistiveDeviceApplicationFormDao dao;
	@Autowired
	private EditLogDao editLogDao;
	
	@GetMapping("/Backstage/getAllAssistiveDeviceApplicationForm")
	public String findAllAssistiveDeviceApplicationForm(Model model,@RequestParam(name="p",defaultValue = "1")Integer pageNumber) {
		Page<AssistiveDeviceApplicationForm> page = service.findByPage(pageNumber);
		model.addAttribute("page",page);
		return "Backstage/jsp/allAssistiveDeviceApplicationFormPage";
		
	}
	
	@GetMapping("/goAddForm")
	public String goAddForm(Model model) {
		model.addAttribute("assistiveDevices",new AssistiveDeviceApplicationForm());
		return "/Auxiliary/buyAssistiveDevice";
	}
	
	
	@PostMapping("/addForm")
	public String addForm(@ModelAttribute(name="assistiveDevices")AssistiveDeviceApplicationForm form,RedirectAttributes redirectAttributes) {
		service.addForm(form);
		
		redirectAttributes.addFlashAttribute("submitted","表單已提交，請注意來電，靜候聯絡，謝謝");
		return "redirect:/goAddForm";
	}
	
	
	
	
	
	
	@GetMapping("/Backstage/goEditForm")
	public String goEditForm(@RequestParam("id")Integer id,Model model) {
		Optional<AssistiveDeviceApplicationForm> someForm = dao.findById(id);
		Integer id2 = someForm.get().getId();
		model.addAttribute("formId",id2);
		model.addAttribute("someForm",someForm);
		
		return "Backstage/jsp/editAssistDeviceFormPage";
	}

	@PostMapping("/Backstage/editForm")
	public String editForm(@ModelAttribute("someForm") AssistiveDeviceApplicationForm form,Authentication logger) {
		AssistiveDeviceApplicationForm update = dao.save(form);
		Integer id = update.getId();
		service.editLog(logger, "編輯", "表單", id, new Date());
		
		return "redirect:getAllAssistiveDeviceApplicationForm";
	}
	
	
	
	
	
	
	
	@GetMapping("/Backstage/deleteAssistiveDeviceApplicationForm")
	public String deleteAssistiveDevice(@RequestParam("id") Integer id, Authentication logger ) {
		service.editLog(logger, "刪除", "輔具表單", id, new Date());
		
		//刪除
		dao.deleteById(id);

		return "redirect:getAllAssistiveDeviceApplicationForm";
	}
	
	
	
	
	
	
	
}
