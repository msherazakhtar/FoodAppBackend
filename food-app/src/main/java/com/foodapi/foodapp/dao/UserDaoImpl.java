package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.Util.EmailUtil;
import com.foodapi.foodapp.Util.OperationalEnums;
import com.foodapi.foodapp.Util.GeneralOperations;
import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetUserDetails;
import com.foodapi.foodapp.models.ORMGetUsers;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMSaveUsers;
import com.foodapi.foodapp.models.ParamList;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		ORMResponse resp = new ORMResponse();
		if (!total.equals("0")) {
			resp = generalOperations.setResponse("User", OperationalEnums.Already_Exists, result.toString());
			return resp;
		} else {

			// TODO: Encrytp password from frontend
//        	ormUser.setPassword(new BCryptPasswordEncoder().encode(ormUser.getPassword()));
			if (ormUser.getUser_id() == null || ormUser.getUser_id().toString().equals("")) {
				Long verificationCode = generalOperations.genRandomNumber((long) 999999);
				String emailText = "Please use code " + verificationCode + " to verify your account.";
				EmailUtil email = new EmailUtil();
				email.sendEmail("MyFoodApp", ormUser.getEmail(), "Verification Code", emailText);
				ormUser.setVerification_code(verificationCode.toString());
				result = db.saveEntity(ormUser, DBOperations.Option.ADD);
			} else {
				result = db.saveEntity(ormUser, DBOperations.Option.EDIT);
			}

		}
		resp = generalOperations.setResponse("Category", OperationalEnums.Save, result.toString());
		return resp;
	}

	@Override
	public List<ORMGetUsers> getUser(String account_id) {
		List<ParamList> paramList = new ArrayList<>();
		paramList.add(new ParamList("account_id", account_id, String.class));
		return db.getStoredProcedureData("spGetAllUsers", ORMGetUsers.class, paramList);
	}

	@Override
	public List<ORMGetUserDetails> getUserDetails(String user_id) {
		List<ParamList> paramList = new ArrayList<>();
		paramList.add(new ParamList("user_id", user_id, String.class));
		return db.getStoredProcedureData("spGetUserDetails", ORMGetUserDetails.class, paramList);
	}
}
