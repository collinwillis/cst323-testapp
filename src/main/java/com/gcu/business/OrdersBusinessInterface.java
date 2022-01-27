package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

public interface OrdersBusinessInterface {
	
	public void init();
	
	public void destroy();
	
	public void test();
	
	public List<OrderModel> getOrders();
	public int delete(int id);
	public int update(OrderModel order);
	public OrderModel findById(int id);
	public boolean create(OrderModel order);
	
	
	
	

}
