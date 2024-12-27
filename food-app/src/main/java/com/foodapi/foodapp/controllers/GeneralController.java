package com.foodapi.foodapp.controllers;

import com.foodapi.foodapp.Services.GeneralService;

	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/general")
public class GeneralController {
	@Autowired
	GeneralService generalService;

	

}
