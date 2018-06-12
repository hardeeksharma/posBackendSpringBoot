package com.pawan.pos.service;

import java.util.List;

import com.pawan.pos.dto.CashDrawerDto;
import com.pawan.pos.model.CashDrawer;
import com.pawan.pos.model.Employee;

public interface CashDrawerService {

	boolean createCashDrawer(Employee emp, float startBal);
	
	CashDrawerDto getCashDrawerDto(CashDrawer cashDrawer);

	List<CashDrawerDto> getCashDrawerListDto(List<CashDrawer> cashDrawer);

}
