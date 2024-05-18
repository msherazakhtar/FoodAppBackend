package com.foodapi.foodapp.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.dao.GeneralDao;
import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMGetAllRestaurants;
import com.foodapi.foodapp.models.ORMValidateUser;
import com.foodapi.foodapp.models.ParamList;

@Service
public class GeneralOperations {
	@Autowired
	DBOperations db;
	 private static final Logger logger = LoggerFactory.getLogger(GeneralOperations.class);

	    public static void logMessage(String message) {
	    	String loggingMessage = "---->>>>> " +message+" <<<<<----";
//	        logger.info(loggingMessage);
	        System.out.println(loggingMessage);
	    }

		public List<ORMValidateUser> validateUser(String email, String password) {
			List<ParamList> paramList = new ArrayList<>();
	        paramList.add(new ParamList("email",email, String.class));
	        paramList.add(new ParamList("password",password, String.class));

	        List<ORMValidateUser> validatedUser = db.getStoredProcedureData("spValidateUser", ORMValidateUser.class,paramList);
	        return validatedUser;
		}
		public Long genRandomNumber(Long maxNumber) {
			Long number = (long)0;
			if(maxNumber>0)
			{
				Random generator = new Random();
				number = generator.nextLong(maxNumber);
			}
			else {
				return null;
			}
			return number;
		}
}
