package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao{
    @Autowired
    DBOperations db;
    @Autowired
    GeneralOperations generalOperations;
    @Override
    public List<ORMGetAllRestaurants> getAllRestaurants(String account_id) {
        List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("account_id",account_id, String.class));
        return db.getStoredProcedureData("spGetAllRestaurants", ORMGetAllRestaurants.class,paramList);
    }

    @Override
    public ORMResponse saveRestaurant(ORMSaveRestaurants orm) {
        Integer result = 0;
        ORMResponse resp = new ORMResponse();
        if(orm!=null)
        {
            if(orm.getRestaurant_id() == null || orm.getRestaurant_id().toString().equals(""))
            {
                result = db.saveEntity(orm, DBOperations.Option.ADD);

            }
            else
            {
              result =  db.saveEntity(orm, DBOperations.Option.EDIT);
            }
        }
        resp = generalOperations.setResponse("Category", OperationalEnums.Save, result.toString());
		return resp;

    }

    @Override
    public List<ORMGetRestaurantDetails> getRestaurantDetails(String restaurant_id) {
        List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("restaurant_id",restaurant_id, String.class));
        return db.getStoredProcedureData("spGetRestaurantDetails", ORMGetRestaurantDetails.class,paramList);
    }
}
