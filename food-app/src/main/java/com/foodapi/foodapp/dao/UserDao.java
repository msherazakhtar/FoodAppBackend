package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    ORMResponse saveUser(ORMSaveUsers ormUser);

    List<ORMGetUsers> getUser(String account_id);

    List<ORMGetUserDetails> getUserDetails(String user_id);
}
