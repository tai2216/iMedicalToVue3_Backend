package com.jerryboot.springbootdemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jerryboot.springbootdemo.dao.AdministratorDao;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.model.Administrator;

@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorDao dao; 
	
	@Autowired
	private EditLogDao editLogDao;
	//@Autowired
	//private BCryptPasswordEncoder encoder;
	
	
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
	
	
	/*
	 * 查詢所有管理者
	 * @return List型態物件
	 * */
	public List<Administrator> findAllAdmin(){
		return dao.findAll();
	}
	
	
	/*
	 * 創建新的管理者帳號
	 * @param 輸入帳號密碼
	 * @return 不回傳
	 * */
	public void insertAdmin(Administrator admin) {
		//將輸入的密碼以演算法加密
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(admin.getAdminPassword());
//		admin.setAdminPassword(encodedPassword);
		
		dao.saveAndFlush(admin);
	}
	
	/*
	 * 輸入管理者的id進行查詢
	 * @return 查詢的結果將回傳page型態
	 * */
	public Page<Administrator> findByPage(Integer pageNumber){
		PageRequest page = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<Administrator> pageObject = dao.findAll(page);
		return pageObject;
	}
	
	
	
	public List<Administrator> checkLogin(String loginAccount,String loginPassword){
		
		//使用編碼器將收到的密碼加密後再進入資料庫做比對是否相符
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String encodedPassword = encoder.encode(loginPassword);
		List<Administrator> loginResult = dao.findByAccountAndPassword(loginAccount, loginPassword);
		
		if(loginResult!=null) {
			return loginResult;
		}
		
		return null;
	}
	
	

}
