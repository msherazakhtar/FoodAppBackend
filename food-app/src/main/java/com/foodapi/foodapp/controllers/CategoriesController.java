package com.foodapi.foodapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Services.CategoriesService;
import com.foodapi.foodapp.Services.GeneralService;
import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.models.ORMDeleteRecord;
import com.foodapi.foodapp.models.ORMGetAllCategories;
import com.foodapi.foodapp.models.ORMGetCategoryDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveCategory;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
	CategoriesService categoriesService;
	@Autowired
	GeneralService generalService;
	@Autowired
	GeneralOperations generalOperations;
	
	
	@PostMapping("/addCategory")
	public ResponseEntity<ORMResponse> addCategory(@RequestBody ORMSaveCategory orm)
	{
		ORMResponse resp = categoriesService.addCategory(orm);
		return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/getCategories/{account_id}")
	public List<ORMGetAllCategories> getCategories(@PathVariable(value = "account_id") String account_id)
	{
		List<ORMGetAllCategories> lst = categoriesService.getCategories(account_id);
		return lst;
	}
	
	@GetMapping("/getCategoryDetails/{category_id}")
	public List<ORMGetCategoryDetails> getCategoryDetails(@PathVariable(value = "category_id") String category_id)
	{
		List<ORMGetCategoryDetails> details = categoriesService.getCategoryDetails(category_id);
		return details;
	}
	
	
	 @PostMapping("/deleteCategory")
	    public  ResponseEntity<ORMResponse> deleteCategory(@RequestBody ORMDeleteRecord ormDeleteRecord)
	    {
	        ormDeleteRecord.setTable_name("categories");
	        ormDeleteRecord.setColumn_name("category_id");
	        ORMResponse resp = new ORMResponse();
	          Integer result = generalService.deleteRecord(ormDeleteRecord);
	          resp = generalOperations.setResponse("Category", OperationalEnums.Delete, result.toString());
	        return  new ResponseEntity<>(resp,HttpStatus.OK);

	    }

}
