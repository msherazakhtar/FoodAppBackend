package com.foodapi.foodapp.controllers;

import com.foodapi.foodapp.Services.GeneralService;
import com.foodapi.foodapp.Services.UserService;
import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.models.ORMDeleteRecord;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UsersController {
	@Autowired
	UserService userService;
	@Autowired
	GeneralService generalService;
	@Autowired
	GeneralOperations generalOperations;

	@PostMapping("/saveUser")
	public ResponseEntity<ORMResponse> saveUser(@RequestBody ORMSaveUsers ormUser) {
		ORMResponse resp = userService.saveUser(ormUser);
		return new ResponseEntity<ORMResponse>(resp, HttpStatus.OK);
	}

	@GetMapping("/getUsers/{account_id}")
	public List<ORMGetUsers> getUsers(@PathVariable(value = "account_id") String account_id) {
		List<ORMGetUsers> lstUsers = userService.getUsers(account_id);
		return lstUsers;
	}

	@GetMapping("/getUserDetails/{user_id}")
	public ResponseEntity<List<ORMGetUserDetails>> getUserDetails(@PathVariable(value = "user_id") String user_id) {
		List<ORMGetUserDetails> ormUser = userService.getUserDetails(user_id);
		return new ResponseEntity<>(ormUser, HttpStatus.OK);
	}

	@PostMapping("/deleteUser")
	public ResponseEntity<ORMResponse> deleteUser(@RequestBody ORMDeleteRecord ormDeleteRecord) {
		ormDeleteRecord.setTable_name("users");
		ormDeleteRecord.setColumn_name("user_id");
		ORMResponse resp = new ORMResponse();
		Integer result = generalService.deleteRecord(ormDeleteRecord);
		resp = generalOperations.setResponse("User", OperationalEnums.Delete, result.toString());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}
}
