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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.dao.EmployeeDao;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.service.EditLogService;
import com.jerryboot.springbootdemo.service.EmployeeService;


@SuppressWarnings("unchecked")
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeDao dao;

	
	@Autowired
	private EditLogDao editLogDao;
	@Autowired
	private EditLogService editLogService;
	

	
	
	@GetMapping("/Backstage/getallEmployee")
	public String findAllMember(Model model,@RequestParam(name="p",defaultValue = "1")Integer pageNumber) {
		
		Page<Employee> page = service.findByPage(pageNumber);
		model.addAttribute("page",page);
//		List<Employee> allEmployee = service.getAllEmployee();
//		model.addAttribute("empList",allEmployee);
		return "Backstage/jsp/allEmployee";
	}
	
	@GetMapping(value="/Backstage/downloadImage/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> downloadImage(@PathVariable("id") Integer id){
		Employee photoById = service.searchEmployeeById(id);
		
		byte[] photoFile = photoById.getEmployeePhoto();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG); //MediaType.IMAGE_JPEG
		//此陣列物件裡面放的是1.要回傳的物件2.header3.httpstatus回應
		return new ResponseEntity<byte[]>(photoFile,httpHeaders,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/Backstage/employeelogin")
	public String employeeLoginPage(HttpSession session) {
		
		if(session.getAttribute("loginSession")!=null) {
			session.setMaxInactiveInterval(0);
			
			return "Backstage/jsp/index";
		}else {
			return "Backstage/jsp/login";
		}
	}
//	//security
//	@GetMapping("/Backstage/employeeloginError")
//	public String employeeLoginErrorPage(RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute("loginError", "帳號密碼輸入錯誤，請重新輸入");
//		return "Backstage/jsp/login";
//	}
	
	
	@PostMapping("/Backstage/employeepost")
	public String postForm(@RequestParam("username") String loginUser,
							@RequestParam("password") String loginPassword,
							HttpSession httpSession,
							RedirectAttributes redirectAttributes) {
		List<Employee> result = service.checkLogin(loginUser, loginPassword);
		httpSession.setAttribute("loginEmployee", result);
		if(result.isEmpty()==true) {
			redirectAttributes.addFlashAttribute("loginError", "帳號密碼輸入錯誤，請重新輸入");
			
			return "redirect:employeelogin";
		}else{ 
			httpSession.setAttribute("loginSession", result);
		return "Backstage/jsp/loginOK";
	}	
	}
	
	
//	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}
//					,value="/Backstage/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response , HttpSession session,RedirectAttributes redirectAttributes,Authentication auth) {
//		if(session!=null) {
//					System.out.println("這是session: "+session);
//					session.invalidate();
//		
//		Cookie[] cs = request.getCookies();
//		
//		for (Cookie c : cs) {
//			if ("JSESSIONID".equals(c.getName())) {
//				c.setMaxAge(0);
//				response.addCookie(c);
//			}
//		}
//		}
//		
//		if(!auth.getAuthorities().isEmpty()) {
//			System.out.println("這是authentication: "+auth.getAuthorities().toString());
//			auth.getAuthorities().clear();
//		}
//
//		
//		redirectAttributes.addFlashAttribute("logoutMessage", "您已登出，請重新登入後使用");
//		return "redirect:/Backstage/adminLogin";
//	}
	
	
	
//	@GetMapping("/employee/allEmployee")
//	public String viewEmployeePage(Model model,@RequestParam(name="p",defaultValue = "1") Integer pageNumber) {
//		Page<Employee> page = service.findByPage(pageNumber);
//		
//		model.addAttribute("page",page);
//		return "allEmployee";
//				
//		
//	}
	
	
	@GetMapping("/Backstage/goAddEmployee")
	public String addEmployeePage(Model model) {
		model.addAttribute("employeeBean",new Employee());
		
		return "Backstage/jsp/addEmployeePage";
		
	}
	
	@PostMapping("/Backstage/addEmployee")
	public String addEmployee(@ModelAttribute (name="employeeBean")Employee addEmp,@RequestParam("employeePic") MultipartFile employeePic,Authentication logger) throws IOException {
//		String fileName = StringUtils.cleanPath(employeePhoto.getOriginalFilename());
//		System.out.println("fileName: "+fileName);
//		addEmp.setEmployeePhoto(fileName);
//		System.out.println("123");

		
		Employee addEmployee = service.addEmployee(addEmp,employeePic);
		
		Integer id = addEmployee.getId();
		
		service.editLog(logger, "新增", "員工", id,new Date());
		
//		String uploadDir = "./employee-avatar/"+addEmployee.getId();
//		System.out.println("uploadDir: "+uploadDir);
//		Path uploadPath = Paths.get(uploadDir);
//		System.out.println("uploadPath: "+uploadPath);
//		if(!Files.exists(uploadPath)) {
//			Files.createDirectories(uploadPath);
//		}
//		
//		try(InputStream inputStream = employeePhoto.getInputStream()) {
//			Path filePath = uploadPath.resolve(fileName);
//			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//			
//		}catch (IOException e) {
//			throw new IOException("Could not save uploaded file: "+ fileName);
//		}
		

		
		return "redirect:getallEmployee";
	}
	
	
	@GetMapping("/Backstage/editemployee")
	public String editEmployeePage(@RequestParam("id") Integer id, Model model) {
		Optional<Employee> someEmp = dao.findById(id);
		Integer id2 = someEmp.get().getId();
		model.addAttribute("empId",id2);
		model.addAttribute("someEmployee",someEmp);
		return "Backstage/jsp/editEmployeePage";
	}
	
	
	
	@PostMapping("/Backstage/editEmployee")
	public String editEmployee(@ModelAttribute("employeeBean") Employee emp,@RequestParam("employeePic") MultipartFile employeePic,Authentication logger,Date editTime)throws IOException {
		Employee addEmployee = service.addEmployee(emp, employeePic);
		Integer id = addEmployee.getId();
		
		service.editLog(logger, "編輯", "員工", id,new Date());
		
		
		
		return "redirect:getallEmployee";
		
	}
	
	
	@GetMapping("/Backstage/deleteEmployee")
	public String deleteEmployee(@RequestParam("id") Integer id, Authentication logger,String action,String tableName,Integer updatedTableId,Date editTime) {
		//紀錄		
		service.editLog(logger, "刪除", "員工", id,new Date());
		//刪除
		dao.deleteById(id);
		
		return "redirect:getallEmployee";
	}
	
	
	@GetMapping("Backstage/searchEmployee")
	public String searchEmployeeByIdOrName(@RequestParam("id") Integer id,Model model) {

		Employee emp = service.searchEmployeeById(id);

		model.addAttribute("empById",emp);
		return "Backstage/jsp/searchPages/searchEmployee";
	}
	
	@RequestMapping("Backstage/checkAccount")
	@ResponseBody
	public String checkAccount(@RequestParam("account")  String account) {
		Employee result = dao.findEmployeeByAccount(account);
		//若從資料庫中找到此帳號則返回無法使用的字串給前端的check function 
		//若查無帳號則代表帳號可以使用 返回可使用此帳號的字串
		if(result!=null) {
			String no ="此帳號無法使用";
			return no;
		}else {
			String yes= "可使用此帳號";
			return yes;
		}
		
	}
	
	
	
	

}
