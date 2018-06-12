package com.pawan.pos.dao;

import java.util.List;

import com.pawan.pos.model.Customer;

public interface CustomerDAO {

	List<Customer> getCustomers(String search);

	public Customer getCustomersById(int id);

	void updateCustomer(Customer customer);
}
