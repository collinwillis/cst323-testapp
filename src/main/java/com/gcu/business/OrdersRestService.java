package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.OrderModel;
import com.gcu.model.OrderList;

@RestController
@RequestMapping("/service")
public class OrdersRestService {
	
	
	@Autowired
	OrdersBusinessInterface service;
	
	@GetMapping(path="/getjson", produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<OrderModel> getOrdersAsJson()
	{
		return service.getOrders();
	}
	
	@GetMapping(path="/getxml", produces= {MediaType.APPLICATION_XML_VALUE})
	public OrderList getOrdersAsXML()
	{
		OrderList list = new OrderList();
		list.setOrders(service.getOrders());
		return list;
	}

}
