package com.foodapi.foodapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;
import com.foodapi.foodapp.models.ParamList;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	DBOperations db;
	@Autowired
    GeneralOperations generalOperations;
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
	        resp = generalOperations.setResponse("Category", OperationalEnums.Save, result.toString());
			return resp;
	}
	@Override
	public List<ORMGetOrdersList> getOrdersList(String account_id) {
		List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("account_id",account_id,String.class));
        return db.getStoredProcedureData("spGetOrdersList", ORMGetOrdersList.class,paramList);
	}

}
