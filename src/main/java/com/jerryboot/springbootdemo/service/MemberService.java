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

import com.jerryboot.springbootdemo.dao.EditLogDao;
import com.jerryboot.springbootdemo.dao.MemberDao;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.model.Member;


@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private EditLogDao editLogDao;
	//@Autowired
	//private BCryptPasswordEncoder encoder;
	
	public List<Member> getAllMember() {
		return dao.findAll();
	}
	
	public static void saveMember(Member newRegister) {
		// TODO Auto-generated method stub
		
	}
//// -------------------------- 前台  //---------------------------------	
	
	// 登入成功
		public List<Member> checkLogin (String loginAccount, String loginPassword) {
			List<Member> login = dao.findByNameAndPassword(loginAccount, loginPassword);
			
			
			
			
			if(login!=null) {
				return login;
			}
			
			return null;
		}
		
		// 註冊成功
		public List<Member> checkAdd (String loginAccount, String loginPassword) {
			List<Member> login = dao.findByNameAndPassword(loginAccount, loginPassword);
			
			
			
			
			if(login!=null) {
				return login;
			}
			
			return null;
		}
		public Page<Member> findByPage2(Integer pageNumber){
			PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
			Page<Member> page = dao.findAll(pgb);
			
			return page;
		}

		public Member addMember2(Member mem) {
			return dao.save(mem);
		}
	
	
	
	
	
	

//////////////////////////////// 後台 ///////////////////////////	

	public Member addMember(Member mem) {
		return dao.save(mem);
	}
	
	public Page<Member> findByPage(Integer pageNumber){
		PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
		Page<Member> page = dao.findAll(pgb);
		
		return page;
	}
	
	
	public Member searchMemberById(Integer id){
		Optional<Member> findById = dao.findById(id);
	
		if(findById.isPresent()==true) {
			Member member = findById.get();
			return member;
		}else {
			return null;
		}
		
	}
	//AJAX用
	public Member finByAccount(String account) {
		Member memberAccount = dao.findMemberByAccount(account);
				
		if(memberAccount!=null) {
			return memberAccount;
		}
				
		return null;
	}
			
			
			
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


	
	
}
