package com.foodapi.foodapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
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
	@Autowired
	GeneralOperations generalOperations;

	@Override
	public ORMResponse addCategory(ORMSaveCategory orm) {
		
		
		Integer result = 0;
		ORMResponse resp = new ORMResponse();
		if(orm.getCategory_id() == null || orm.getCategory_id().toString().equals("")) 
		{
			result = db.saveEntity(orm, Option.ADD);
		}
		else
		{
			result = db.saveEntity(orm, Option.EDIT);
		}
		resp = generalOperations.setResponse("Category", OperationalEnums.Save, result.toString());
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
