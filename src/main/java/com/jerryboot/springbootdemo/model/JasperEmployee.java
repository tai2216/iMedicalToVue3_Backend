package com.jerryboot.springbootdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jasper_employee")
public class JasperEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="jasper_employee_id")
	private Integer id;
	
	@Column(name="jasper_employee_name", columnDefinition = "nvarchar(20)",nullable = false)
	private String employeeName;
	
	@Column(name="jasper_employee_phone",columnDefinition = "nvarchar(20)" , nullable = false)
	private String employeePhone;
	
	@Column(name="jasper_employee_email", columnDefinition = "nvarchar(100)",nullable = false)
	private String employeeEmail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public JasperEmployee(Integer id, String employeeName, String employeePhone, String employeeEmail) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
	}
	
	
	
	
}
