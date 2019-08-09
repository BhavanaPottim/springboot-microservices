package com.example.demo.service;

import com.example.demo.model.request.UserDetailsRequestModel;
import com.example.demo.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
