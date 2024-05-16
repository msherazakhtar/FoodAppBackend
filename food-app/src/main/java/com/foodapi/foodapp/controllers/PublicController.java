package com.foodapi.foodapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Services.UserService;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;
import com.foodapi.foodapp.models.ORMSaveUsers;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	UserService userService;
	@RequestMapping("/hello")
	public String hello()
	{
		return "JWT Less Controller";
	}
	@RequestMapping("/signUp")
    public ResponseEntity<ORMResponse> signUp(@RequestBody ORMSaveUsers ormUser)
    {
        ORMResponse resp = userService.saveUser(ormUser);
        return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
    }
	
}
