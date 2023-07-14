package com.jerryboot.springbootdemo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	//@Autowired
	//private BCryptPasswordEncoder encoder;
	
	
	private enum EmployeeCellStyle{
		HEADER_CELL(IndexedColors.GREY_25_PERCENT,IndexedColors.BLACK,true),
		NORMAL_CELL(IndexedColors.WHITE,IndexedColors.BLACK,false);	
		
		private IndexedColors fgIdxColor;
		private IndexedColors fontIdxColor;
		private boolean fontBold;
		

		private EmployeeCellStyle(IndexedColors fgIdxColor, IndexedColors fontIdxColor, boolean fontBold) {
			this.fgIdxColor = fgIdxColor;
			this.fontIdxColor = fontIdxColor;
			this.fontBold = fontBold;
		}
		CellStyle createCellStyle(Workbook wb) {
			CellStyle cs = wb.createCellStyle();
			cs.setFillForegroundColor(fgIdxColor.getIndex());
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Font font = wb.createFont();	
			font.setColor(fontIdxColor.getIndex());
			font.setBold(fontBold);
			cs.setFont(font);			
			return cs;
		}
	}
	
    public Workbook createEmployeeWorkbook(List<Employee> empDatas) {
    	Workbook wb = new XSSFWorkbook();
    	Sheet sheet = wb.createSheet("employee");
    	createRowCells(sheet , 0 , EmployeeCellStyle.HEADER_CELL.createCellStyle(wb) ,
	    				   "ID","Name","Account","Role" , "Phone" , "Email",
	    				   "Address","Birthday","Salary","Onboard");
    	for(int i =1;i <empDatas.size()-1;i++) {
//    		for (Employee data:empDatas) {
//    			wb.getSheet("employee").setRowBreak(i);
//    		}
    		for(Employee empItem:empDatas) {		    	
		    	createRowCells(sheet ,i++ ,EmployeeCellStyle.NORMAL_CELL.createCellStyle(wb) ,
		    			empItem.getId().toString() ,empItem.getEmployeeName(),
		    			empItem.getEmployeeAccount(),empItem.getEmployeeRole(),
		    			empItem.getEmployeePhone() ,empItem.getEmployeeEmail(),
		    			empItem.getEmployeeAddress(),empItem.getEmployeeBirthday().toString(),
		    			empItem.getEmployeeSalary().toString(),
		    			empItem.getEmployeeOnboard().toString()
		    			);
	    	}
    	}
    		
    	return wb;
    }
    public void createRowCells(Sheet sheet,int rowNum,CellStyle style,String... cellValues) {
    	Row row = sheet.createRow(rowNum);
    	for(int cellNum = 0 ; cellNum < cellValues.length ; cellNum++) {
    		Cell cell = row.createCell(cellNum);
    		String cellValue = cellValues[cellNum];
    		cell.setCellValue(cellValue);
    		cell.setCellStyle(style);
    	}
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
		if(photo.length==0) {
			emp.setEmployeePhoto(searchEmployeeById(emp.getId()).getEmployeePhoto());
		}
		else{
			emp.setEmployeePhoto(photo);
		}
		
		//Bcrptencoder加密密碼存入資料庫
		//emp.setEmployeePassword(encoder.encode(emp.getEmployeePassword()));
		return dao.saveAndFlush(emp);
	}

	
	
	public boolean edit(@RequestBody Employee employeeData) throws IOException {
		Optional<Employee> data = dao.findById(employeeData.getId());
		//使用ID找尋要編輯的員工資料 有找到才繼續
		if(data.isPresent()) {
			Employee employee = data.get();
			if(employeeData.getEmployeePhoto()!=null && employeeData.getEmployeePhoto().length>0) {
				employee.setEmployeePhoto(employeeData.getEmployeePhoto());
			}
			employee.setEmployeeAccount(employeeData.getEmployeeAccount());
			employee.setEmployeePassword(employeeData.getEmployeePassword());
			employee.setEmployeeName(employeeData.getEmployeeName());
			employee.setEmployeeRole(employeeData.getEmployeeRole());
			employee.setEmployeePhone(employeeData.getEmployeePhone());
			employee.setEmployeeEmail(employeeData.getEmployeeEmail());
			employee.setEmployeeAddress(employeeData.getEmployeeAddress());
			employee.setEmployeeBirthday(employeeData.getEmployeeBirthday());
			employee.setEmployeeSalary(employeeData.getEmployeeSalary());
			employee.setEmployeeOnboard(employeeData.getEmployeeOnboard());
			dao.save(employee);
			return true;
		}
		return false;
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
