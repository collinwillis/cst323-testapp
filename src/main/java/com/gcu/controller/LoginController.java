package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

@Controller
public class LoginController {
	
	@Autowired
	OrdersBusinessInterface service;
	
	@GetMapping("/")
	public String display(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin( @Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		//Orders Business Interface call
		service.test();
		
		if(bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		
		return "orders";
	}

}
