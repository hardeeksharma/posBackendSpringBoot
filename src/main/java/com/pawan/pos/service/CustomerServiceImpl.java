package com.pawan.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.CustomerDAO;
import com.pawan.pos.dto.CustomerDto;
import com.pawan.pos.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public List<Customer> getCustomers(String search) {
		return customerDAO.getCustomers(search);
	}

	@Override
	public CustomerDto getCustomerDto(Customer customer) {
		return new CustomerDto(customer);
	}

	@Override
	public List<CustomerDto> getCustomerDtolist(List<Customer> customers) {
		List<CustomerDto> customerDtos = new ArrayList<>();

		for (Customer customer : customers)
			customerDtos.add(new CustomerDto(customer));
		return customerDtos;
	}

	@Override
	public Customer getCustomersById(int cid) {
		
		return customerDAO.getCustomersById(cid);
		
	}

}