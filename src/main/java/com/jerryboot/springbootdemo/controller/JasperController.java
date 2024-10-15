package com.jerryboot.springbootdemo.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jerryboot.springbootdemo.dao.EmployeeDao;
import com.jerryboot.springbootdemo.model.Employee;
import com.jerryboot.springbootdemo.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class JasperController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@PostMapping("/Employee/PDF")
	public ResponseEntity<byte[]> generateEmployeePDF(HttpServletRequest request) throws Exception, JRException, IOException, NoSuchFileException {
		System.out.println("進入/Employee/PDF");
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employeeDao.findAll());
		JasperReport compileReport = null;
		try (FileInputStream fis = new FileInputStream("src/main/webapp/WEB-INF/jasper_report/Lab_1.jrxml")){
			compileReport = JasperCompileManager.compileReport(fis);
		}
		HashMap<String, Object> map = new HashMap<>();
		
		//Get the path to put it in the parameter "jasper_report_real_path"
		ServletContext servletContext = request.getServletContext();
		String realPath = servletContext.getRealPath("/WEB-INF/jasper_report");
		map.put("jasper_report_real_path",realPath);
		
		JasperPrint fillReport = JasperFillManager.fillReport(compileReport, map,jrBeanCollectionDataSource);
		byte[] data = JasperExportManager.exportReportToPdf(fillReport);
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=employee.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@PostMapping("/Employee/Excel")
	public void generateEmployeeExcel(HttpServletResponse response) throws Exception, JRException {
		List<Employee> empDatas = employeeDao.findAll();
		
		try (Workbook wb = employeeService.createEmployeeWorkbook(empDatas);){
			String mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	    	response.setContentType(mimeType);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=EmployeeList.xlsx");
			try(ServletOutputStream os = response.getOutputStream();){
				wb.write(os);
			}
		}
		
		//return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	
	
}
