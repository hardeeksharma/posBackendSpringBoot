package com.pawan.pos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawan.pos.dao.CartDAO;
import com.pawan.pos.dao.CashDrawerDAO;
import com.pawan.pos.dao.CustomerDAO;
import com.pawan.pos.dao.EmployeeDAO;
import com.pawan.pos.dao.OrderDAO;
import com.pawan.pos.dao.ProductDAO;
import com.pawan.pos.dao.Product_CartDAO;
import com.pawan.pos.dao.Product_OrderDAO;
import com.pawan.pos.dto.OrderDto;
import com.pawan.pos.dto.Product_OrderDto;
import com.pawan.pos.exception.CustomException;
import com.pawan.pos.model.Cart;
import com.pawan.pos.model.CashDrawer;
import com.pawan.pos.model.Customer;
import com.pawan.pos.model.Employee;
import com.pawan.pos.model.Orders;
import com.pawan.pos.model.Product;
import com.pawan.pos.model.Product_Cart;
import com.pawan.pos.model.Product_Order;
import com.pawan.pos.utils.Constants;
import com.pawan.pos.utils.DateParse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private Product_CartDAO product_CartDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Product_OrderDAO product_OrderDAO;

	@Autowired
	private CashDrawerDAO cashDrawerDAO;

	@Autowired
	private CartService cartService;

	@Override
	public Orders createOrder(int custId, int empId, String status, String paymode, String orderId) {

		float amount = 0;
		Orders order = null;

		Customer customer = customerDAO.getCustomersById(custId);
		Employee employee = employeeDAO.getEmployeeById(empId);
		Cart cart = cartDAO.getCartByCustomerId(custId);
		List<Product_Cart> carts = product_CartDAO.getCartItems(cart.getId());

		if (!orderId.equals("")) {
			order = orderDAO.getOrderByOrderId(orderId);
			order.setStatus(status);
			order.setPaymentType(paymode);
		} else {
			order = new Orders(generateOrderID(), status, customer, employee, paymode);
			orderDAO.createOrder(order);
			employee.getOrder().add(order);
			customer.getOrders().add(order);
		}

		for (Product_Cart product_Cart : carts) {

			Product_Order product_Order = new Product_Order();
			Product product = productDAO.getProductById(product_Cart.getProduct().getId());

			product.getOrder().add(product_Order);
			product_Order.setProducts(product);

			order.getOrder().add(product_Order);
			product_Order.setOrders(order);

			product_Order.setQuantity(product_Cart.getQuantity());
			amount = amount + product_Cart.getQuantity() * product_Cart.getProduct().getPrice();

			if (status.equals(Constants.COMPLETE)) {
				final int updatedStock = product.getStock() - product_Cart.getQuantity();
				if (updatedStock >= 0) {
					product.setStock(updatedStock);
				}
			}

			product_OrderDAO.createProductOrder(product_Order);
			productDAO.updateProduct(product);
		}

		order.setAmount(amount);
		order.setTax(Constants.TAX);

		if (orderId.equals("")) {
			employeeDAO.updateEmployee(employee);
			customerDAO.updateCustomer(customer);
		}
		orderDAO.updateOrder(order);
		cartService.deleteCart(custId);

		CashDrawer cashDrawer = employee.getCashDrawer().get(employee.getCashDrawer().size() - 1);

		if (status.equals(Constants.COMPLETE)) {
			cashDrawer.setEndBal(cashDrawer.getEndBal() + amount);
			cashDrawerDAO.UpdateCashDrawer(cashDrawer);
		}
		return orderDAO.getOrderById(order.getId());
	}

	public String generateOrderID() {
		Date date = new Date();
		String orderID = "" + date.getTime();
		return orderID;
	}

	@Override
	public Orders reloadSavedOrder(String orderId, int empId) {
		Orders order = orderDAO.getSavedOrderByOrderId(orderId, empId);

		for (Product_Order product_Order : order.getOrder()) {
			cartService.addProductToCart(order.getCustomer().getId(), product_Order.getProducts().getId(),
					product_Order.getQuantity());
			product_OrderDAO.removeProduct_Order(order.getId());
		}
		return order;
	}

	@Override
	public Map<String, List<OrderDto>> getCompleteOrderList(int empId) throws CustomException {

		Employee employee = employeeDAO.getEmployeeById(empId);
		if (employee.getOrder().isEmpty())
			throw new CustomException("No Orders exist!");

		final Map<String, List<OrderDto>> sortedOrders = new TreeMap<>(Collections.reverseOrder());
		final List<Orders> ordersList = employee.getOrder();

		for (final Orders currOrder : ordersList) {
			if (!currOrder.getOrder().isEmpty()) {
				final String date = DateParse.parseDate(currOrder.getOrderDate().toString().split(" ")[0]);
				if (sortedOrders.containsKey(date)) {
					sortedOrders.get(date).add(new OrderDto(currOrder));
				} else {
					sortedOrders.put(date, new ArrayList<OrderDto>());
					sortedOrders.get(date).add(new OrderDto(currOrder));
				}
			}
		}
		return sortedOrders;
	}

	@Override
	public Map<String, List<OrderDto>> getSavedOrderList(int empId) throws CustomException {

		Employee employee = employeeDAO.getEmployeeById(empId);
		if (employee.getOrder().isEmpty())
			throw new CustomException("No Orders exist!");

		final Map<String, List<OrderDto>> sortedOrders = new TreeMap<>();
		final List<Orders> ordersList = orderDAO.getOrderList(empId, Constants.INCOMPLETE);
		System.out.println(ordersList.size());

		for (final Orders currOrder : ordersList) {
			if (!currOrder.getOrder().isEmpty()) {
				final String date = DateParse.parseDate(currOrder.getOrderDate().toString().split(" ")[0]);
				if (sortedOrders.containsKey(date)) {
					sortedOrders.get(date).add(new OrderDto(currOrder));
				} else {
					sortedOrders.put(date, new ArrayList<OrderDto>());
					sortedOrders.get(date).add(new OrderDto(currOrder));
				}
			}
		}
		return sortedOrders;
	}

	@Override
	public List<Orders> getCompleteOrderListByOrderID(int empId, String orderId) {
		return orderDAO.getOrderListByOrderID(empId, orderId, Constants.COMPLETE);
	}

	@Override
	public List<Orders> getSavedOrderListByOrderID(int empId, String orderId) {
		return orderDAO.getOrderListByOrderID(empId, orderId, Constants.INCOMPLETE);
	}

	@Override
	public List<Orders> getOrderById(int empId, String orderId) {
		return orderDAO.getOrderByOrderId(empId, orderId);
	}

	@Override
	public OrderDto getOrderDto(Orders order) {
		return new OrderDto(order);
	}

	@Override
	public List<OrderDto> getOrderDtoList(List<Orders> order) {
		List<OrderDto> dtos = new ArrayList<>();
		for (Orders orders : order) {
			dtos.add(new OrderDto(orders));
		}
		return dtos;
	}

	@Override
	public List<Product_Order> getOrderProduct(int orderId) {
		return product_OrderDAO.getProduct_OrderById(orderId);
	}

	@Override
	public List<Product_OrderDto> getProductOrderListDto(List<Product_Order> product_Orders) {
		List<Product_OrderDto> dtos = new ArrayList<>();
		for (Product_Order product_Order : product_Orders) {
			dtos.add(new Product_OrderDto(product_Order));
		}
		return dtos;
	}
}
