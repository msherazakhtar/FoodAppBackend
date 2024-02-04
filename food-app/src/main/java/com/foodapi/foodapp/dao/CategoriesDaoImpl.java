package com.foodapi.foodapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.db.DBOperations.Option;
import com.foodapi.foodapp.models.ORMGetAllCategories;
import com.foodapi.foodapp.models.ORMGetCategoryDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveCategory;
import com.foodapi.foodapp.models.ParamList;

@Repository
public class CategoriesDaoImpl implements CategoriesDao {
	@Autowired
	DBOperations db;

	@Override
	public ORMResponse addCategory(ORMSaveCategory orm) {
		
		
		int result = 0;
		ORMResponse resp = new ORMResponse();
		if(orm.getCategory_id() == null || orm.getCategory_id().toString().equals("")) 
		{
			result = db.saveEntity(orm, Option.ADD);
		}
		else
		{
			result = db.saveEntity(orm, Option.EDIT);
		}
		if(result == 1)
		{
			resp.setMessage("Data Saved Successfully.");
			resp.setResult("1");
			resp.setStatus("Success");
		}
		else {
			resp.setMessage("Data Can Not Be Saved");
			resp.setResult("0");
			resp.setStatus("Error");
		}
		return resp;
	}

	@Override
	public List<ORMGetAllCategories> getCategories(String account_id) {
		List<ParamList> paramList = new ArrayList<>();
		paramList.add(new ParamList("account_id", account_id, String.class));
		List<ORMGetAllCategories> lst = db.getStoredProcedureData("spGetAllCategories", ORMGetAllCategories.class, paramList);
		return lst;
	}

	@Override
	public List<ORMGetCategoryDetails> getCategoryDetails(String category_id) {
		List<ParamList> paramList = new ArrayList<>();
		paramList.add(new ParamList("category_id", category_id, String.class));
		List<ORMGetCategoryDetails> details = db.getStoredProcedureData("spGetCategoryDetails", ORMGetCategoryDetails.class, paramList);
		return details;
	}

}
