package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.request.UserDetailsRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.UserService;
import com.example.demo.shared.Utils;
@Service
public class UserServiceImplementation implements UserService{
	
	Map<String,UserRest> users;
	Utils utils;
	public UserServiceImplementation() {}
	
	@Autowired
	public UserServiceImplementation(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest userRest = new UserRest();
		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setEmail(userDetails.getEmail());
		String userId=utils.generateUserId();
		userRest.setUserId(userId);
		
		if(users == null) {
			users= new HashMap<>();
			users.put(userId, userRest);
		}
		return userRest;
	}

}
