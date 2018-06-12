package com.pawan.pos.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.Employee;
import com.pawan.pos.model.EmployeeSecret;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Employee getLogin(Employee employee) {

		Query query = getCurrentSession().createQuery("from Employee where email=:Email").setParameter("Email",
				employee.getEmail());

		Employee emp = (Employee) query.uniqueResult();
		if (emp == null)
			return null;

		query = getCurrentSession().createQuery("from EmployeeSecret where id=:Id and pass=:Pass")
				.setParameter("Id", emp.getId()).setParameter("Pass", employee.getEmployeeSecret().getPass());

		EmployeeSecret employeeSecret = (EmployeeSecret) query.uniqueResult();

		if (employeeSecret == null)
			return null;
		return emp;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Query query = getCurrentSession().createQuery("from Employee where id=:ID").setParameter("ID", empId);
		return (Employee) query.uniqueResult();
	}

	@Override
	public void updateEmployee(Employee emp) {
		getCurrentSession().update(emp);
	}

}
