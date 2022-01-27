package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessInterface {
	
	@Autowired
	DataAccessInterface<OrderModel> service;

	@Override
	public void test() {
		System.out.println("Hello from the OrdersBusinessService");	
	}

	@Override
	public void init() {
		System.out.println("In the OrdersBusinessService.init()");	
		
	}

	@Override
	public void destroy() {
		System.out.println("In the OrdersBusinessService.destroy()");	
		
	}

	@Override
	public List<OrderModel> getOrders() {
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.findAll();
		
		return orders;
		
	}

	@Override
	public int delete(int id) {
		int result = service.delete(id);
		return result;
	}

	@Override
	public int update(OrderModel order) {
		return service.update(order);
	}

	@Override
	public OrderModel findById(int id) {
		OrderModel order = service.findById(id);
		return order;
	}

	@Override
	public boolean create(OrderModel order) {
		// TODO Auto-generated method stub
		return service.create(order);
	}
	
	
	
	

}
