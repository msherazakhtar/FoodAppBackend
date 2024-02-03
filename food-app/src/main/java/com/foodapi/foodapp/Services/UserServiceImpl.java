package com.foodapi.foodapp.Services;

import com.foodapi.foodapp.dao.UserDao;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;


    @Override
    public ORMResponse saveUser(ORMSaveUsers ormUser) {
      return userDao.saveUser(ormUser);
    }

    @Override
    public List<ORMGetUsers> getUsers(String account_id) {
       return userDao.getUser(account_id);
    }

    @Override
    public List<ORMGetUserDetails> getUserDetails(String user_id) {
        return userDao.getUserDetails(user_id);
    }
}
