package com.pawan.pos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EmployeeSecret extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 3773463361373167739L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String pass;

	public EmployeeSecret() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
