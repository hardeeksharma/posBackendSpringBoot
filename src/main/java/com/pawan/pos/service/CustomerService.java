package com.pawan.pos.service;

import java.util.List;

import com.pawan.pos.dto.CustomerDto;
import com.pawan.pos.model.Customer;

public interface CustomerService {

	List<Customer> getCustomers(String search);

	CustomerDto getCustomerDto(Customer customer);

	List<CustomerDto> getCustomerDtolist(List<Customer> customers);

	Customer getCustomersById(int cid);

}
