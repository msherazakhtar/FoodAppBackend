package com.foodapi.foodapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.dao.OrderDao;
import com.foodapi.foodapp.models.ORMGetOrdersList;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveOrder;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	@Override
	public ORMResponse saveOrder(ORMSaveOrder orm) {
		return orderDao.saveOrder(orm);
	}
	@Override
	public List<ORMGetOrdersList> getOrdersList(String account_id) {
		return orderDao.getOrdersList(account_id);
	}

}
