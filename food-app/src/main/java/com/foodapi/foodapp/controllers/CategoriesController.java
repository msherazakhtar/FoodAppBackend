package com.foodapi.foodapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.foodapp.Services.CategoriesService;
import com.foodapi.foodapp.Services.GeneralService;
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
	
	
	@RequestMapping("/addCategory")
	public ResponseEntity<ORMResponse> addCategory(@RequestBody ORMSaveCategory orm)
	{
		ORMResponse resp = categoriesService.addCategory(orm);
		return new ResponseEntity<ORMResponse>(resp,HttpStatus.OK);
	}
	
	@RequestMapping("/getCategories/{account_id}")
	public List<ORMGetAllCategories> getCategories(@PathVariable(value = "account_id") String account_id)
	{
		List<ORMGetAllCategories> lst = categoriesService.getCategories(account_id);
		return lst;
	}
	
	@RequestMapping("/getCategoryDetails/{category_id}")
	public List<ORMGetCategoryDetails> getCategoryDetails(@PathVariable(value = "category_id") String category_id)
	{
		List<ORMGetCategoryDetails> details = categoriesService.getCategoryDetails(category_id);
		return details;
	}
	
	
	 @RequestMapping("/deleteCategory")
	    public  ResponseEntity<ORMResponse> deleteCategory(@RequestBody ORMDeleteRecord ormDeleteRecord)
	    {
	        ormDeleteRecord.setTable_name("categories");
	        ormDeleteRecord.setColumn_name("category_id");
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
