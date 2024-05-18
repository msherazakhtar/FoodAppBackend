package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.Util.EmailUtil;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import com.foodapi.foodapp.models.ParamList;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    DBOperations db;
    @Autowired
    GeneralOperations generalOperations;
    @Override
    public ORMResponse saveUser(ORMSaveUsers ormUser) {
        Integer result = 0;
        List<ParamList> lstParam = new ArrayList<ParamList>();
        lstParam.add(new ParamList("email", ormUser.getEmail(), String.class));
        String total = db.getSingleColumnStoredProcedureData("spCheckUserAccount", lstParam);
        ORMResponse resp  =new ORMResponse();
        if(!total.equals("0"))
        {
        	resp.setStatus("Not Allowed");
            resp.setMessage("This Email Is Already Registered");
            resp.setResult(result.toString());
            return resp;
        }
        else {
        	ormUser.setPassword(new BCryptPasswordEncoder().encode(ormUser.getPassword()));
            if(ormUser.getUser_id() ==null || ormUser.getUser_id().toString().equals(""))
            {
            	Long verificationCode = generalOperations.genRandomNumber((long)999999);
            	String emailText =  "Please use code "+verificationCode+" to verify you account.";
            	EmailUtil  email =  new EmailUtil();
            	email.sendEmail("MyFoodApp", ormUser.getEmail(), "Verification Code", emailText);
            	ormUser.setVerification_code(verificationCode.toString());
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
