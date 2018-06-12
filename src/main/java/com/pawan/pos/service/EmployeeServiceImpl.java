package com.pawan.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.EmployeeDAO;
import com.pawan.pos.dto.EmployeeDto;
import com.pawan.pos.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee getLogin(Employee employee) {
		return employeeDAO.getLogin(employee);
	}

	@Override
	public Employee getEmployeeById(int empId) {
		return employeeDAO.getEmployeeById(empId);
	}

	@Override
	public EmployeeDto getEmployeeDto(Employee emp) {
		final EmployeeDto dto = new EmployeeDto(emp);
		return dto;
	}

}
