package com.pawan.pos.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.model.Employee;
import com.pawan.pos.service.CashDrawerService;
import com.pawan.pos.service.EmployeeService;
import com.pawan.pos.utils.Constants;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CashDrawerService cashDrawerService;

	@PostMapping(value = "/employees")
	public ResponseEntity<Object> Login(HttpSession session, @ModelAttribute Employee employee,
			@RequestParam("start_bal") float startBal) {
		Employee emp = employeeService.getLogin(employee);

		if (emp == null)
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		cashDrawerService.createCashDrawer(emp, startBal);
		session.setAttribute(Constants.EMPLOYEE, emp);

		return ResponseEntity.ok(employeeService.getEmployeeDto(emp));
	}
}
