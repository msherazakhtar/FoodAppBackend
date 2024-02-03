package com.foodapi.foodapp.dao;

import java.util.List;

import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;

public interface OrderDao {

	ORMResponse saveOrder(ORMSaveOrder orm);

	List<ORMGetOrdersList> getOrdersList(String account_id);

}
