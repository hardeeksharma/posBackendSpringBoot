package com.pawan.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class CashDrawer extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -6707215241586336366L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date date;
	private float startBal;
	private float endBal;

	@JsonIgnore
	@ManyToOne
	private Employee employee;

	public CashDrawer() {
	}

	public CashDrawer(float startBal2, Employee emp) {
		this.date = new Date();
		this.startBal = startBal2;
		this.endBal = startBal2;
		this.employee = emp;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getStartBal() {
		return startBal;
	}

	public void setStartBal(float startBal) {
		this.startBal = startBal;
	}

	public float getEndBal() {
		return endBal;
	}

	public void setEndBal(float f) {
		this.endBal = f;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
