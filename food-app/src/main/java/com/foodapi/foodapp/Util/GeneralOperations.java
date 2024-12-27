package com.foodapi.foodapp.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMResponse;
import com.foodapi.foodapp.models.ORMValidateUser;
import com.foodapi.foodapp.models.ParamList;

@Service
public class GeneralOperations {
	@Autowired
	DBOperations db;
	private static final Logger logger = LoggerFactory.getLogger(GeneralOperations.class);

	// Messasge Loging
	public static void logMessage(String message) {
		String loggingMessage = "---->>>>> " + message + " <<<<<----";
//	        logger.info(loggingMessage);
		System.out.println(loggingMessage);
	}

	// User Validation
	public List<ORMValidateUser> validateUser(String email, String password) {
		List<ParamList> paramList = new ArrayList<>();
		paramList.add(new ParamList("email", email, String.class));
		paramList.add(new ParamList("password", password, String.class));

		List<ORMValidateUser> validatedUser = db.getStoredProcedureData("spValidateUser", ORMValidateUser.class,
				paramList);
		return validatedUser;
	}

	// Random Number Generator
	public Long genRandomNumber(Long maxNumber) {
		Long number = (long) 0;
		if (maxNumber > 0) {
			Random generator = new Random();
			number = generator.nextLong(maxNumber);
		} else {
			return null;
		}
		return number;
	}

	// Response Generator
	public ORMResponse setResponse(String moduleName, OperationalEnums message, String result) {
		ORMResponse resp = new ORMResponse();
		if (message.equals(OperationalEnums.Already_Exists)) {
			resp.setMessage(moduleName + " With this EMAIL already exists");
			resp.setResult(result);
			resp.setStatus(ResponseEnums.Unauthorized);
			return resp;
		}
		if (!result.equals("0")) {
			if (message.equals(OperationalEnums.Save)) {
				resp.setMessage(moduleName + " Saved Successfully");
				resp.setResult(result);
				resp.setStatus(ResponseEnums.Success);
			} else {
				resp.setMessage(moduleName + " Deleted Successfully");
				resp.setResult(result);
				resp.setStatus(ResponseEnums.Success);
			}
		} else {
			if (message.equals(OperationalEnums.Save)) {

				resp.setMessage("Failed to save " + moduleName);
				resp.setResult(result);
				resp.setStatus(ResponseEnums.Failure);
			} else {
				resp.setMessage("Failed to delete " + moduleName);
				resp.setResult(result);
				resp.setStatus(ResponseEnums.Failure);
			}
		}
		return resp;
	}
}
