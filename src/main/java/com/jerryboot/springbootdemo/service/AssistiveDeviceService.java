package com.jerryboot.springbootdemo.service;

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

import com.jerryboot.springbootdemo.dao.AssistiveDeviceDao;
import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.model.AssistiveDevice;

@Service
@Transactional
public class AssistiveDeviceService {
	
	
	@Autowired
	private AssistiveDeviceDao dao;
	
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
	
	
	public void save(AssistiveDevice ad) {
		dao.save(ad);
	}
	public List<AssistiveDevice> allAssistiveDevice(){
		List<AssistiveDevice> findAll = dao.findAll();
		return findAll;
	}
	
	public AssistiveDevice findById(Integer id){
		Optional<AssistiveDevice> option = dao.findById(id);
		if(option.isPresent()) {
			AssistiveDevice ad = option.get();
			return ad;
		}
		return null;
	}
	
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	
	
	
	
	
	
	
	
	///////////////////////////////////
	//////////////豫台/////////////////
	/////////////////////////////////
	
	public List<AssistiveDevice> getAllDevice(){
		return dao.findAll();
	}
	
	public Page<AssistiveDevice> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<AssistiveDevice> page = dao.findAll(pgb);
		return page;
	}
	
	public AssistiveDevice addDevice(AssistiveDevice device) {
		return dao.save(device);
	}
	
	
	public AssistiveDevice searchAssistiveDeviceById(Integer id){
		Optional<AssistiveDevice> findById = dao.findById(id);
	
		if(findById.isPresent()==true) {
			AssistiveDevice assistiveDevice = findById.get();
			return assistiveDevice;
		}else {
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
