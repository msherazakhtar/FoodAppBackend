package com.foodapi.foodapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Services.OrderService;
import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/saveOrder")
	public ResponseEntity<ORMResponse> saveOrder(@RequestBody ORMSaveOrder orm)
	{
		ORMResponse resp = orderService.saveOrder(orm);
		return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
	}
	
	@RequestMapping("/getOrdersList/{account_id}")
	public List<ORMGetOrdersList> getOrdersList(@PathVariable(value = "account_id") String account_id)
	{
		List<ORMGetOrdersList> lst = orderService.getOrdersList(account_id);
		return lst;
	}
	
}
