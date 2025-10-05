package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserRequest;
import com.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/createuser")
	public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest){
		UserRequest newUserRequest = userService.createUser(userRequest);
		
		return new ResponseEntity<UserRequest>(newUserRequest, HttpStatus.OK);
	}
}
