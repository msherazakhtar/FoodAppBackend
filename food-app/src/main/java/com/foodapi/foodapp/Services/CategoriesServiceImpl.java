package com.foodapi.foodapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.dao.CategoriesDao;
import com.foodapi.foodapp.models.ORMGetAllCategories;
import com.foodapi.foodapp.models.ORMGetCategoryDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveCategory;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	CategoriesDao categoriesDao;

	@Override
	public ORMResponse addCategory(ORMSaveCategory orm) {
		return categoriesDao.addCategory(orm);
	}

	@Override
	public List<ORMGetAllCategories> getCategories(String account_id) {
		return categoriesDao.getCategories(account_id);
	}

	@Override
	public List<ORMGetCategoryDetails> getCategoryDetails(String category_id) {
		return categoriesDao.getCategoryDetails(category_id);
	}

}
