package com.foodapi.foodapp.Services;

import java.util.List;

import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;

public interface OrderService {

	ORMResponse saveOrder(ORMSaveOrder orm);

	List<ORMGetOrdersList> getOrdersList(String account_id);

}
