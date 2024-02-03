package com.foodapi.foodapp.Services;

import java.util.List;

import com.foodapi.foodapp.models.ORMGetAllCategories;
import com.foodapi.foodapp.models.ORMGetCategoryDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveCategory;

public interface CategoriesService {

	ORMResponse addCategory(ORMSaveCategory orm);

	List<ORMGetAllCategories> getCategories(String account_id);

	List<ORMGetCategoryDetails> getCategoryDetails(String category_id);

	
}
