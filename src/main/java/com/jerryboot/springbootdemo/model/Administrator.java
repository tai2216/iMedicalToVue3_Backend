package com.jerryboot.springbootdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="administrator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Administrator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="administrator_id")
	private Integer id;
	@Column(name="administrator_account",columnDefinition="nvarchar(500)")
	private String adminAccount;
	@Column(name="administrator_password",columnDefinition = "nvarchar(500)")
	private String adminPassword;
	//@ColumnDefault(value = "ADMIN,NORMAL,ROLE_MANAGER")
	@Column(name="administrator_role")
	private String adminRole;
	

	public Administrator() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAdminAccount() {
		return adminAccount;
	}


	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}


	public String getAdminPassword() {
		return adminPassword;
	}


	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	public String getAdminRole() {
		return adminRole;
	}


	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}


	public Administrator(Integer id, String adminAccount, String adminPassword, String adminRole) {
		super();
		this.id = id;
		this.adminAccount = adminAccount;
		this.adminPassword = adminPassword;
		this.adminRole = adminRole;
	}
	
	
	
	
	
	
	
	

}
