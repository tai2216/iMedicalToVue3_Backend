package com.jerryboot.springbootdemo.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="assistive_device_application_form")
public class AssistiveDeviceApplicationForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="assistive_device_application_form_id")
	private Integer id;
	@Column(name="assistive_device_application_form_name",columnDefinition = "nvarchar(20)")
	private String name;
	@Column(name="assistive_device_application_form_gender",columnDefinition = "varchar(50)")
	private String gender;
	@Column(name="assistive_device_application_form_phone",columnDefinition = "nvarchar(50)")
	private String phone;
	@Column(name="assistive_device_application_form_address",columnDefinition = "nvarchar(100)")
	private String address;
	@Column(name="assistive_device_application_form_device",columnDefinition = "nvarchar(100)")
	private String device;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_member_id")
	private Member member;
	
	
	
	public AssistiveDeviceApplicationForm() {
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDevice() {
		return device;
	}



	public void setDevice(String device) {
		this.device = device;
	}



	public Member getMember() {
		return member;
	}



	public void setMember(Member member) {
		this.member = member;
	}



	@Override
	public String toString() {
		return "AssistiveDeviceApplicationForm [id=" + id + ", name=" + name + ", gender=" + gender + ", phone=" + phone
				+ ", address=" + address + ", device=" + device + ", member=" + member + "]";
	}
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
