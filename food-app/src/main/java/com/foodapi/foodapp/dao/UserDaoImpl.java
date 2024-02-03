package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import com.foodapi.foodapp.models.ParamList;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    DBOperations db;
    @Override
    public ORMResponse saveUser(ORMSaveUsers ormUser) {
        Integer result = 0;
        ORMResponse resp  =new ORMResponse();
        if(ormUser.getUser_id() ==null || ormUser.getUser_id().toString().equals(""))
        {
            result=  db.saveEntity(ormUser, DBOperations.Option.ADD);
        }
        else {
            result =  db.saveEntity(ormUser, DBOperations.Option.EDIT);
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
            resp.setResult(result.toString());
        }
    return resp;
    }

    @Override
    public List<ORMGetUsers> getUser(String account_id) {
        List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("account_id",account_id,String.class));
        return db.getStoredProcedureData("spGetAllUsers", ORMGetUsers.class,paramList);
    }

    @Override
    public List<ORMGetUserDetails> getUserDetails(String user_id) {
        List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("user_id",user_id,String.class));
        return db.getStoredProcedureData("spGetUserDetails", ORMGetUserDetails.class,paramList);
    }
}
