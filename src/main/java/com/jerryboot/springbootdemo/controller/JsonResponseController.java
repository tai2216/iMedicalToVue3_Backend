package com.jerryboot.springbootdemo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jerryboot.springbootdemo.dao.AdministratorDao;
import com.jerryboot.springbootdemo.dao.EmployeeDao;
import com.jerryboot.springbootdemo.model.Administrator;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.service.AdministratorService;
import com.jerryboot.springbootdemo.service.EmployeeService;
import com.jerryboot.springbootdemo.util.UtilStaticFactory;

@RestController
public class JsonResponseController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private AdministratorService administratorService;
	// 回傳json格式字串
	@GetMapping("/relatedLink")
	public String returnRefLink(HttpServletRequest req) {	
		
		// 取得當前執行環境路徑
		String path = req.getServletContext().getRealPath("");
		
		String jsonFileUrl = path + "/WEB-INF/" + "json/relatedLink.json";
		String jsonString = UtilStaticFactory.returnJsonString(jsonFileUrl);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/empList", 
			method = {RequestMethod.POST,RequestMethod.GET},
			produces = {"application/json"})
	@ResponseBody
	public Map<Integer,Object> getEmployeeList() {
		List<Employee> list = employeeDao.findAll();
		Map<Integer,Object> map = new HashMap();
		for(int i=0;i<list.size()-1;i++) {
			map.put(i, list.get(i));
		}
		return map;
	}
	
	@RequestMapping(value="/login",
					method= RequestMethod.POST,
					produces= {"application/json"})
	public Map<String, Object> logIn(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String loginAccount = request.getParameter("username");
		String loginPassword = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		System.out.println("account: "+loginAccount+" password: "+loginPassword);
		List<Administrator> loginResult = administratorService.checkLogin(loginAccount, loginPassword);
		//找到的登入者去除掉密碼的部分傳回前端
		loginResult.get(0).setAdminPassword("");
		//一般登入(不勾選記住我)
		boolean nomalLogin = !loginResult.isEmpty()&loginResult!=null;
		//一般登入(有勾選記住我)
		boolean rememberLogin = !loginResult.isEmpty()&loginResult!=null & rememberMe.equals("true");
		//取得客戶端的網域 後續設置cookie路徑使用 ex: http://localhost:8080/
		String clientPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
		if(rememberLogin) {
			Cookie cookie = new Cookie("login", "true");
			cookie.setPath(clientPath);
			cookie.setMaxAge(cookie.getMaxAge());
			response.addCookie(cookie);
			Map<String, Object> map = new HashMap();
			map.put("token", "aaasvsv4991sv");
			map.put("message", "Log In Success!");
			map.put("loginEmployee", loginResult);
			return map;
		}else if(nomalLogin) {
			Cookie cookie = new Cookie("login", "true");
			cookie.setPath(clientPath);
			cookie.setMaxAge(60*30);
			response.addCookie(cookie);
			Map<String, Object> map = new HashMap();
			map.put("token", "aaasvsv4991sv");
			map.put("message", "Log In Success!");
			map.put("loginEmployee", loginResult);
			return map;
		}
		else {
			//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			Map<String, Object> map = new HashMap();
			map.put("message", "Log In Failed, please check again");
			return map;
		}
	}
	
	


	
	@PostMapping("/checkLogin")
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
		String parameter = request.getParameter("login");
		if(parameter.equals("true")) {return true;}
		
		return false;
	}
	
	
	@GetMapping(value="/getEmployeeDataToEdit",produces= {"application/json"})
	@ResponseBody
	public Map<Object, Object> getEmployeeDataToEdit(@RequestParam("id") Integer id) {
		Optional<Employee> someEmp = employeeDao.findById(id);
		if(!someEmp.isEmpty()) {
			Employee employee = someEmp.get();
			System.out.println("有取得"+new Date());
			return employee.toMap(employee);
		}
		System.out.println("沒取得"+new Date());
		return null;
		
	}
	
	@PostMapping("/editEmployeeData")
	public String editEmployeeData(
			@RequestBody Employee employeeData,
			Authentication logger,
			Date editTime)throws IOException {
		
		boolean  editSuccess= employeeService.edit(employeeData);
		if(editSuccess) {
			//employeeService.editLog(logger, "編輯", "員工", employeeData.getId(),new Date());
			return "編輯成功";
		}
		
		return "編輯失敗";
		
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("id") Integer id) {
		employeeDao.deleteById(id);
		return "刪除成功";
	}
	
	
	
	
	
}
