package com.foodapi.foodapp.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMValidateUser;

@RestController
@RequestMapping("/validator")
public class UserValidator {
		@Autowired
		GeneralOperations generalOperations;
	 @RequestMapping("/validateUser/{email}/{password}")
	    public  ResponseEntity<List<ORMValidateUser>> getUserDetails(@PathVariable(value = "email") String email,
	    		@PathVariable(value = "password") String password)
	    {
	        List<ORMValidateUser> validatedUser = generalOperations.validateUser(email,password);
	        if(validatedUser == null || validatedUser.size() <1)
	        {
	        	return  new ResponseEntity<>(validatedUser,HttpStatus.UNAUTHORIZED);
	        }
	        else {
	        	return  new ResponseEntity<>(validatedUser,HttpStatus.OK);
	        }
	        
	    }

}
