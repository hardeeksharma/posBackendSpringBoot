package com.pawan.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.CashDrawerDAO;
import com.pawan.pos.dao.EmployeeDAO;
import com.pawan.pos.dto.CashDrawerDto;
import com.pawan.pos.model.CashDrawer;
import com.pawan.pos.model.Employee;

@Service
public class CashDrawerServiceImpl implements CashDrawerService {

	@Autowired
	private CashDrawerDAO cashDrawerDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public boolean createCashDrawer(Employee emp, float startBal) {

		CashDrawer cashDrawer = new CashDrawer(startBal, emp);
		emp.getCashDrawer().add(cashDrawer);
		cashDrawerDAO.saveCashDrawer(cashDrawer);
		employeeDAO.updateEmployee(emp);
		return true;
	}

	@Override
	public CashDrawerDto getCashDrawerDto(CashDrawer cashDrawer) {
		return new CashDrawerDto(cashDrawer);
	}

	@Override
	public List<CashDrawerDto> getCashDrawerListDto(List<CashDrawer> cashDrawer) {
		List<CashDrawerDto> list = new ArrayList<>();
		for (CashDrawer cash : cashDrawer) {
			list.add(new CashDrawerDto(cash));
		}
		return list;
	}

}
