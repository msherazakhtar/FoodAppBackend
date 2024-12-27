package com.foodapi.foodapp.controllers;

import com.foodapi.foodapp.Services.GeneralService;
import com.foodapi.foodapp.Services.RestaurantService;
import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    GeneralService generalService;
    @Autowired
    GeneralOperations generalOperations;

    @GetMapping("/getAllRestaurants/{account_id}")
    public ResponseEntity<List<ORMGetAllRestaurants>> getAllRestaurants(@PathVariable String account_id)
    {
        List<ORMGetAllRestaurants> lst = restaurantService.getAllRestaurants(account_id);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("/saveRestaurant")
    public ResponseEntity<ORMResponse> saveRestaurant(@RequestBody ORMSaveRestaurants orm)
    {
       ORMResponse resp = restaurantService.saveRestaurant(orm);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @PostMapping("/deleteRestaurant")
    public  ResponseEntity<ORMResponse> deleteUser(@RequestBody ORMDeleteRecord ormDeleteRecord)
    {
        ormDeleteRecord.setTable_name("restaurants");
        ormDeleteRecord.setColumn_name("restaurant_id");
        ORMResponse resp = new ORMResponse();
        Integer result = generalService.deleteRecord(ormDeleteRecord);
        resp = generalOperations.setResponse("User", OperationalEnums.Delete, result.toString());
        return  new ResponseEntity<>(resp,HttpStatus.OK);

    }
    @GetMapping("/getRestaurantDetails/{restaurant_id}")
    public  ResponseEntity<List<ORMGetRestaurantDetails>> getRestaurantDetails(@PathVariable String restaurant_id)
    {
        List<ORMGetRestaurantDetails> lst = restaurantService.getRestaurantDetails(restaurant_id);
        return  new ResponseEntity<>(lst,HttpStatus.OK);
    }
}
