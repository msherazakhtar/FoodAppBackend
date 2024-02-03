package com.foodapi.foodapp.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.dao.GeneralDao;

@Service
public class GeneralOperations {
	 private static final Logger logger = LoggerFactory.getLogger(GeneralDao.class);

	    public static void logMessage(String message) {
	        logger.info(message);
	    }
}
