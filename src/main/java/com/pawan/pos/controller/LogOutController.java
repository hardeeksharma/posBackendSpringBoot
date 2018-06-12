package com.pawan.pos.controller;

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

@RestController
@RequestMapping(value = "/logout")
public class LogOutController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CashDrawerService cashDrawerService;

	@GetMapping
	public ResponseEntity<Object> logOut(HttpSession session) {

		Employee employee = employeeService
				.getEmployeeById(((Employee) session.getAttribute(Constants.EMPLOYEE)).getId());
		 session.invalidate();
		CashDrawer cashDrawer = employee.getCashDrawer().get(employee.getCashDrawer().size() - 1);
		return ResponseEntity.ok(cashDrawerService.getCashDrawerDto(cashDrawer));
	}
}
