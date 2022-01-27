package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.business.OrdersBusinessInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrdersBusinessInterface service;
	
	@GetMapping("/")
	public String display( Model model)
	{
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		
		return "orders";
	}
	
	
	@GetMapping("/showAddForm")
	public String displayAdd( Model model)
	{
		
		model.addAttribute("title", "Add Order");
		model.addAttribute("order", new OrderModel(0, null, null, null, 0));
		
		
		return "addOrder";
	}
	
	@GetMapping("/showUpdateForm/{id}")
	public String displayUpdate(@PathVariable (value = "id") int id, Model model)
	{
		OrderModel order = service.findById(id);
		model.addAttribute("order", order);
		model.addAttribute("title", "Add Order");
		
		
		return "updateOrder";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteOrder( @PathVariable (value = "id") int id, Model model)
	{

		int result = service.delete(id);
		if(result > 0)
		{
			System.out.println("Delete Sucessful");
		}
		else {
			System.out.println("Delete failed");
		}
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		
		return "orders";
		
	}
	
	@PostMapping("/saveOrder")
	public String saveOrder(@ModelAttribute("order") OrderModel order, Model model)
	{
		service.update(order);
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		
		return "orders";
		
	}
	@PostMapping("/createOrder")
	public String createOrder(@ModelAttribute("order") OrderModel order, Model model)
	{
		service.create(order);
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		
		return "orders";
		
	}
	


}
