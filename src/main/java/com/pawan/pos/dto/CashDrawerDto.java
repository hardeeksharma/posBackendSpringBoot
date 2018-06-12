package com.pawan.pos.dto;

import com.pawan.pos.model.CashDrawer;
import com.pawan.pos.utils.DateParse;

public class CashDrawerDto {

	private int id;
	private String date;
	private String time;
	private float startBal;
	private float endBal;

	public CashDrawerDto(CashDrawer cashDrawer) {
		this.id = cashDrawer.getId();
		String temp[] = cashDrawer.getDate().toString().split(" ");
		this.date = DateParse.parseDate(temp[0]);
		this.time = temp[1];
		this.startBal = cashDrawer.getStartBal();
		this.endBal = cashDrawer.getEndBal();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setEndBal(float endBal) {
		this.endBal = endBal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
