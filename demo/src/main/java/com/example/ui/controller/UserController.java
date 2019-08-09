package com.example.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.UpdateUserDetailsRequestModel;
import com.example.demo.model.request.UserDetailsRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImplementation;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	Map<String,UserRest> users;
	@GetMapping()
	public String getUsers(@RequestParam(value = "id", defaultValue = "1") String id) {
		return "get userss is called with id" + id;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		String fname=null;
		int length= fname.length();
		if(users.containsKey(userId))
		return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest userRest = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody  UpdateUserDetailsRequestModel userDetails) {
		UserRest storedDetails = users.get(userId);
		storedDetails.setFirstName(userDetails.getFirstName());
		storedDetails.setLastName(userDetails.getLastName());
		users.put(userId, storedDetails);
		return storedDetails;
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity deleteUser(@PathVariable String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}

}
