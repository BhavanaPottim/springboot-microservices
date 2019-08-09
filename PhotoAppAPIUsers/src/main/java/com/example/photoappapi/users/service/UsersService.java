package com.example.photoappapi.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.photoappapi.users.shared.UserDto;

public interface UsersService extends UserDetailsService{
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);

}
