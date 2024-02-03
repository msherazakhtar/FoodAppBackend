package com.foodapi.foodapp.controllers;

import com.foodapi.foodapp.Services.GeneralService;
import com.foodapi.foodapp.Services.UserService;
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

    @RequestMapping("/saveUser")
    public ResponseEntity<ORMResponse> saveUser(@RequestBody ORMSaveUsers ormUser)
    {
        ORMResponse resp = userService.saveUser(ormUser);
        return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
    }
    @RequestMapping("/getUsers/{account_id}")
    public List<ORMGetUsers> getUsers(@PathVariable(value = "account_id" )String account_id){
        List<ORMGetUsers> lstUsers = userService.getUsers(account_id);
        return lstUsers;
    }
    @RequestMapping("/getUserDetails/{user_id}")
    public  ResponseEntity<List<ORMGetUserDetails>> getUserDetails(@PathVariable(value = "user_id") String user_id)
    {
        List<ORMGetUserDetails> ormUser = userService.getUserDetails(user_id);
        return  new ResponseEntity<>(ormUser,HttpStatus.OK);
    }

    @RequestMapping("/deleteUser")
    public  ResponseEntity<ORMResponse> deleteUser(@RequestBody ORMDeleteRecord ormDeleteRecord)
    {
        ormDeleteRecord.setTable_name("users");
        ormDeleteRecord.setColumn_name("user_id");
        ORMResponse resp = new ORMResponse();
          int result = generalService.deleteRecord(ormDeleteRecord);
          if (result == 1)
          {
              resp.setResult("1");
              resp.setMessage("Record Deleted");
              resp.setStatus("Success");
          }
          else {
              resp.setResult("0");
              resp.setMessage("Record Can't Be Deleted");
              resp.setStatus("Error");
          }
        return  new ResponseEntity<>(resp,HttpStatus.OK);

    }
}
