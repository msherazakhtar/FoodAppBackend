package com.foodapi.foodapp.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMJwtUser;
import com.foodapi.foodapp.models.ParamList;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	DBOperations db;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<ParamList> lstParam = new ArrayList<ParamList>();
		lstParam.add(new ParamList("username", username, String.class));
		ORMJwtUser user =  db.getStoredProcedureData("spGetLoginUser", ORMJwtUser.class, lstParam).get(0);
		 return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                new ArrayList<>() // Add roles if needed
	        );
	}

}
