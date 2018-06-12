package com.pawan.pos.service;

import com.pawan.pos.dto.EmployeeDto;
import com.pawan.pos.model.Employee;

public interface EmployeeService {

	public Employee getLogin(Employee employee);

	public Employee getEmployeeById(int id);

	public EmployeeDto getEmployeeDto(Employee emp);

}
