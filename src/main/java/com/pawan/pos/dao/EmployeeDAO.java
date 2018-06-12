package com.pawan.pos.dao;

import com.pawan.pos.model.Employee;

public interface EmployeeDAO {

	Employee getLogin(Employee employee);

	Employee getEmployeeById(int empId);

	void updateEmployee(Employee emp);

}
