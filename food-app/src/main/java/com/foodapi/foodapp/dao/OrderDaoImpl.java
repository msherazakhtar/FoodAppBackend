package com.foodapi.foodapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;
import com.foodapi.foodapp.models.ParamList;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	DBOperations db;
	@Override
	public ORMResponse saveOrder(ORMSaveOrder orm) {
		 Integer result = 0;
	        ORMResponse resp  =new ORMResponse();
	        if(orm.getOrder_id() == null || orm.getOrder_id().toString().equals(""))
	        {
	            result=  db.saveEntity(orm, DBOperations.Option.ADD);
	        }
	        else {
	            result =  db.saveEntity(orm, DBOperations.Option.EDIT);
	        }
	        if(result == 0) {
	            resp.setStatus("Error");
	            resp.setMessage("Failed To Save The Entity");
	            resp.setResult(result.toString());
	        }
	        else
	        {
	            resp.setStatus("Success");
	            resp.setMessage("Data Saved Successfully");
	            resp.setResult(orm.getOrder_id().toString());
	        }
	    return resp;
	}
	@Override
	public List<ORMGetOrdersList> getOrdersList(String account_id) {
		List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("account_id",account_id,String.class));
        return db.getStoredProcedureData("spGetOrdersList", ORMGetOrdersList.class,paramList);
	}

}
