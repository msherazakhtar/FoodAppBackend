package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.models.ORMGetAllRestaurants;
import com.foodapi.foodapp.models.ORMGetRestaurantDetails;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveRestaurants;

import java.util.List;

public interface RestaurantDao {
    List<ORMGetAllRestaurants> getAllRestaurants(String account_id);

    ORMResponse saveRestaurant(ORMSaveRestaurants orm);

    List<ORMGetRestaurantDetails> getRestaurantDetails(String restaurant_id);
}
