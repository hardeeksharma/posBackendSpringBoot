package com.pawan.pos.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.OrderDAO;
import com.pawan.pos.model.Orders;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderDAO dao;

	@Override
	public File excelGenerator(Date sdate, Date edate, int empId) {
		List<Orders> allOrders = dao.getOrderList(empId);

		float amount = 0;
		for (Orders orders : allOrders) {
			amount = amount + orders.getTax() + orders.getAmount();
		}

		StringBuffer str = new StringBuffer(
				"Employee ID || Employee Name || Employee Email || Customer Name || Customer Email || Order ID || Order Status || Payment Type || Amount \n");
		File employee = new File("Employee.csv");
		FileWriter file = null;

		try {
			file = new FileWriter(employee);
			for (Orders emp : allOrders) {
				str.append(emp.toString(emp));
			}
			str.append("\n Total Amount" + amount);
			
			file.write(str.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.getMessage();
		}
		return employee;
	}
}
