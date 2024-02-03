package com.foodapi.foodapp.Services;

import com.foodapi.foodapp.dao.RestaurantDao;
import com.foodapi.foodapp.models.ORMGetAllRestaurants;
import com.foodapi.foodapp.models.ORMGetRestaurantDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveRestaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantDao restaurantDao;
    @Override
    public List<ORMGetAllRestaurants> getAllRestaurants(String account_id) {
        return restaurantDao.getAllRestaurants(account_id);
    }

    @Override
    public ORMResponse saveRestaurant(ORMSaveRestaurants orm) {
        return restaurantDao.saveRestaurant( orm);
    }

    @Override
    public List<ORMGetRestaurantDetails> getRestaurantDetails(String restaurant_id) {
        return restaurantDao.getRestaurantDetails( restaurant_id);
    }
}
