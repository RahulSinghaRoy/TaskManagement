package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.UserRequest;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.UserRepository;
import com.util.IdGenerator;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	@Override
	public UserRequest createUser(UserRequest user) {
		User newUser = objectMapper.convertValue(user, User.class);
		newUser.setId(idGenerator.generateId("USER"));
		User addedUser = userRepository.save(newUser);
		
		return objectMapper.convertValue(addedUser, UserRequest.class);
	}

	

}
