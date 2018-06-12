package com.pawan.pos.dto;

import com.pawan.pos.model.Employee;

public class EmployeeDto {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;

	public EmployeeDto(Employee emp) {
		this.id = emp.getId();
		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
		this.email = emp.getEmail();
		this.mobile = emp.getMobile();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
