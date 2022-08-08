package com.jerryboot.springbootdemo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.dao.EmployeeDao;
import com.jerryboot.springbootdemo.model.Employee;


@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private EditLogDao editLogDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	/*
	 * 此方法為記錄登入者執行的操作
	 * Authentication logger 自動取得 無須設定
	 * 1.String action, 2.String tableName 兩者填入該使用者執行的動作與變更的表格
	 * 3.Integer updatedTableId 放入被更動的資料id
	 * @return 存入editLog不回傳
	 * */
	public void editLog(Authentication username,String action,String tableName,Integer updatedTableId,Date editTime) {
		editLogDao.saveEditLog(username.getName(),action,tableName,updatedTableId,editTime);

	}
	
	
	
	public List<Employee> getAllEmployee(){
		return dao.findAll();
		
	}
	
	

	
	
	public List<Employee> checkLogin (String loginAccount, String loginPassword) {
		List<Employee> login = dao.findByNameAndPassword(loginAccount, loginPassword);
		
		if(login!=null) {
			return login;
		}
		
		return null;
	}
	
	
	public Page<Employee> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<Employee> page = dao.findAll(pgb);
		
		return page;
	}
	
	

	public Employee searchEmployeeById(Integer id){
		Optional<Employee> findById = dao.findById(id);
	
		if(findById.isPresent()==true) {
			Employee employee = findById.get();
			return employee;
		}else {
			return null;
		}
		
	}
	
	
	
	/*
	 * 此方法為save加上將得到上傳圖片的檔案multipart轉換成byte
	 * 此方法放入的參數為(使用者傳入的資料以及照片multipartfile)
	 * @return 一個employee
	 * */
	
	public Employee addEmployee(Employee emp,MultipartFile employeePic) throws IOException{
		byte[] photo = employeePic.getBytes();
		emp.setEmployeePhoto(photo);
		//Bcrptencoder加密密碼存入資料庫
		emp.setEmployeePassword(encoder.encode(emp.getEmployeePassword()));
		return dao.saveAndFlush(emp);
	}

	
	
	//
	public Employee finByAccount(String account) {
		Employee Employee = dao.findEmployeeByAccount(account);
		
		if(Employee!=null) {
			return Employee;
		}
		
		return null;
	}



	/*
	 * check and validate if the account is usable
	 * @param employeeAccount
	 * @return boolean
	 * */
	
	public boolean checkAccount(String employeeAccount) {
		Employee result = dao.findEmployeeByAccount(employeeAccount);
		
		if(result==null) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	

}
