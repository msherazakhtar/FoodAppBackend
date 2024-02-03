package com.foodapi.foodapp.Services;

import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;

import java.util.List;

public interface UserService {
    ORMResponse saveUser(ORMSaveUsers ormUser);

    List<ORMGetUsers> getUsers(String account_id);

    List<ORMGetUserDetails> getUserDetails(String user_id);
}
