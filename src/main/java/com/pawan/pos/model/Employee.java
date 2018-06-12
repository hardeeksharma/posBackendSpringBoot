package com.pawan.pos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Employee extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 4909558636517051495L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Orders> order;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<CashDrawer> cashDrawer;

	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeSecret employeeSecret;

	public Employee() {
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

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	public List<CashDrawer> getCashDrawer() {
		return cashDrawer;
	}

	public void setCashDrawer(List<CashDrawer> cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	public EmployeeSecret getEmployeeSecret() {
		return employeeSecret;
	}

	public void setEmployeeSecret(EmployeeSecret employeeSecret) {
		this.employeeSecret = employeeSecret;
	}

}
