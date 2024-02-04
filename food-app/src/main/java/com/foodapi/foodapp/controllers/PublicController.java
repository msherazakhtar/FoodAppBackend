package com.foodapi.foodapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;

@RestController
@RequestMapping("/public")
public class PublicController {
	@RequestMapping("/hello")
	public String hello()
	{
		return "JWT Less Controller";
	}
	
}
