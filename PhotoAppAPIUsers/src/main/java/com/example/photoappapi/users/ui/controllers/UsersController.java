package com.example.photoappapi.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.photoappapi.users.service.UsersService;
import com.example.photoappapi.users.shared.UserDto;
import com.example.photoappapi.users.ui.model.CreateUserRequestModel;
import com.example.photoappapi.users.ui.model.CreateUsersResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private Environment environment;
	@Autowired
	UsersService usersService;
	
	@GetMapping("/status/check")
	public String getUsers() {
		return "get userss is called with id with token="+environment.getProperty("token.secret")+ "working on port"+environment.getProperty("token.expiration_time") ;
	}
	
	@PostMapping(consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CreateUsersResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		UserDto createdUser = usersService.createUser(userDto);
		CreateUsersResponseModel responseModel  = modelMapper.map(createdUser,CreateUsersResponseModel.class);
		//return new ResponseEntity<CreateUsersResponseModel>(HttpStatus.CREATED).getBody();
		return  ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}

}
