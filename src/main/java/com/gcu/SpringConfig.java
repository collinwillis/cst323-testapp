package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.business.OrdersBusinessService;

@Configuration
public class SpringConfig {
	
	@Bean(name="ordersBusinessService",initMethod="init", destroyMethod="destroy")
	//@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	@SessionScope
	//@RequestScope
	public OrdersBusinessInterface getOrdersBusiness()
	{
		return new OrdersBusinessService();
	}

}
