package com.foodapi.foodapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Services.UserService;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;




@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	UserService userService;
	@GetMapping("/hello")
	public String hello()
	{
		return "JWT Less Controller";
	}
	@PostMapping("/signUp")
    public ResponseEntity<ORMResponse> signUp(@RequestBody ORMSaveUsers ormUser)
    {
        ORMResponse resp = userService.saveUser(ormUser);
        return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
    }
	
}
