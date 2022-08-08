package com.jerryboot.springbootdemo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jerryboot.springbootdemo.dao.AssistiveDeviceDao;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.model.AssistiveDevice;
import com.jerryboot.springbootdemo.model.EditLog;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.model.RealAssistiveDevice;
import com.jerryboot.springbootdemo.service.AssistiveDeviceService;
import com.jerryboot.springbootdemo.service.RealAssistiveDeviceService;

@SuppressWarnings("unchecked")
@Controller
public class AssistiveDeviceController {

	@Autowired
	private AssistiveDeviceDao dao;

	@Autowired
	private AssistiveDeviceService adService;

	@Autowired
	private RealAssistiveDeviceService service;

	@Autowired
	private EditLogDao editLogDao;

	@GetMapping("/Auxiliary/addAuxiliaryPage") // 新增輔具
	public String addAuxiliaryPage(Model model) {
		model.addAttribute("assistiveDevices", new AssistiveDevice());
		return "/Auxiliary/addAuxiliaryPage";
	}

	@GetMapping("/Auxiliary/assistiveDeviceIntroduce") // 輔具介紹
	public String assistiveDeviceIntroduce(Model model,
			@RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
		Page<RealAssistiveDevice> page = service.findByPage(pageNumber);

		model.addAttribute("page", page);
		return "/Auxiliary/assistiveDeviceIntroduce";
	}

	@GetMapping("/Auxiliary/buyAssistiveDevice") // 輔具申請表單
	public String buyAssistiveDevice(Model model) {
		model.addAttribute("assistiveDevices", new AssistiveDevice());
		return "/Auxiliary/buyAssistiveDevice";
	}

	@PostMapping("/addDevices")
	public String postDevice(@ModelAttribute("assistiveDevices") AssistiveDevice postAd) {
		adService.save(postAd);
		return "index";
	}

	@GetMapping("/editAssistiveDevice/{id}")
	public String editDevice(Model model, @PathVariable("id") Integer id) {
		AssistiveDevice ad = adService.findById(id);
		model.addAttribute("assistiveDevices", ad);
		return "editAssistiveDevice";
	}

	@GetMapping("/FrontMember/listassistiveDevice") // 查看申請輔具
	public String listALl(Model model) {
		List<AssistiveDevice> allAssistiveDevice = adService.allAssistiveDevice();
		model.addAttribute("assistiveDeviceList", allAssistiveDevice);
		return "listassistiveDevice";
	}

	@PostMapping("/postEditDevice")
	public String postEditDevice(@ModelAttribute("assistiveDevices") AssistiveDevice postAd) {
		adService.save(postAd);
		return "redirect:/list";
	}

	@GetMapping("deleteDevice/{id}")
	public String deleteDevice(@PathVariable("id") Integer id) {
		adService.delete(id);
		return "redirect:/list";
	}

///////////////////////////////////////////////////////
/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////以下後台///////////////////////////////

	@GetMapping("/Backstage/getAllDevice")
	public String findAllDevice(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
		Page<AssistiveDevice> findByPage = adService.findByPage(pageNumber);
		model.addAttribute("page", findByPage);

		return "Backstage/jsp/allAssistiveDevicePage";
	}

	@GetMapping("/Backstage/goAddAssistiveDevice")
	public String addAssistiveDevicePage(Model model) {
		model.addAttribute("deviceBean", new AssistiveDevice());
		return "Backstage/jsp/addAssistiveDevicePage";

	}

	@PostMapping("/Backstage/addAssistiveDevice")
	public String addAssistiveDevice(@ModelAttribute("deviceBean") AssistiveDevice addDevice,
			@RequestParam("assistiveDevicePic") MultipartFile assistiveDevicePic, Authentication logger )
			throws IOException {

		byte[] photo = assistiveDevicePic.getBytes();

		addDevice.setAssistiveDeviceImg(photo);

		AssistiveDevice addDevice2 = adService.addDevice(addDevice);
		Integer id = addDevice2.getId();
		service.editLog(logger, "新增", "輔具", id, new Date());
		


		return "redirect:../Backstage/getAllDevice";
	}

	@GetMapping("/Backstage/goEditAssistiveDevice")
	public String editAssistiveDevicePage(@RequestParam("id") Integer id, Model model) {
		Optional<AssistiveDevice> someDevice = dao.findById(id);
		model.addAttribute("someDevice", someDevice);
		return "Backstage/jsp/editAssistiveDevicePage";
	}

	@PostMapping("/Backstage/editAssistiveDevice")
	public String editAssistiveDevice(@ModelAttribute("someDevice") AssistiveDevice assistiveDevice,
			@RequestParam("assistiveDevicePic") MultipartFile assistiveDevicePic, Authentication logger )
			throws IOException {

		byte[] photo = assistiveDevicePic.getBytes();

		assistiveDevice.setAssistiveDeviceImg(photo);

		//取得被變更欄位的ID
		AssistiveDevice update = dao.save(assistiveDevice);
		Integer id = update.getId();
		service.editLog(logger, "編輯", "輔具", id, new Date());


		return "redirect:../Backstage/getAllDevice";
	}

	@GetMapping("/Backstage/deleteAssistiveDevice")
	public String deleteAssistiveDevice(@RequestParam("id") Integer id,  Authentication logger) {
		service.editLog(logger, "刪除", "輔具", id, new Date());
		//刪除
		dao.deleteById(id);

		return "redirect:../Backstage/getAllDevice";
	}

	@GetMapping(value = "/Backstage/downloadImageAssistiveDevice/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> downloadImage(@PathVariable("id") Integer id) {
		AssistiveDevice photoById = adService.searchAssistiveDeviceById(id);

		byte[] photoFile = photoById.getAssistiveDeviceImg();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		//此陣列物件裡面放的是1.要回傳的物件2.header3.httpstatus回應
		return new ResponseEntity<byte[]>(photoFile, httpHeaders, HttpStatus.OK);
	}

}
