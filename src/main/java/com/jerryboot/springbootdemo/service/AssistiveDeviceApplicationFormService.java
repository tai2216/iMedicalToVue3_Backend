package com.jerryboot.springbootdemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jerryboot.springbootdemo.dao.AssistiveDeviceApplicationFormDao;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.model.AssistiveDeviceApplicationForm;

@Service
@Transactional
public class AssistiveDeviceApplicationFormService {

	@Autowired
	private AssistiveDeviceApplicationFormDao dao;
	
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
	
	
	
	public List<AssistiveDeviceApplicationForm> getAllForm(){
		return dao.findAll();
	}
	
	public Page<AssistiveDeviceApplicationForm> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<AssistiveDeviceApplicationForm> page = dao.findAll(pgb);
		return page;
	}
	
	
	/*
	 * 此新增表單方法是為開發用，實際上自行新增申請表單並不合理，應由會員新增表單
	 * */
	
	public AssistiveDeviceApplicationForm addForm(AssistiveDeviceApplicationForm form) {
		return dao.save(form);
	}
	
	
	
	
}
