package com.pawan.pos.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.pos.model.Customer;
import com.pawan.pos.service.CustomerService;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.Validation;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<Object> getCustomers(HttpSession session, @RequestParam("search") String search) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		List<Customer> customers = customerService.getCustomers(search);
		return ResponseEntity.ok(customerService.getCustomerDtolist(customers));
	}
	
	@GetMapping(value="/{cid}")
	public ResponseEntity<Object> getCustomersById(HttpSession session, @PathVariable("cid") int cid) {
		if (!Validation.validateSession(session))
			return ResponseEntity.ok(Constants.INVALID_CREDENTIALS);

		Customer customers = customerService.getCustomersById(cid);
		return ResponseEntity.ok(customerService.getCustomerDto(customers));
	}
}
