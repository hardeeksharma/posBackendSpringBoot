package com.pawan.pos.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.model.CashDrawer;
import com.pawan.pos.model.Employee;
import com.pawan.pos.service.CashDrawerService;
import com.pawan.pos.service.EmployeeService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.Validation;

@RestController
@RequestMapping(value = "/cashdrawers")
public class CashDrawerController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CashDrawerService cashDrawerService;

	@GetMapping
	public ResponseEntity<Object> getCashDrawerEmployee(HttpSession session) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		int empId = ((Employee) session.getAttribute(Constants.EMPLOYEE)).getId();
		Employee employee = employeeService.getEmployeeById(empId);
		List<CashDrawer> cashDrawer = employee.getCashDrawer();

		return ResponseEntity.ok(cashDrawerService.getCashDrawerListDto(cashDrawer));
	}

}
