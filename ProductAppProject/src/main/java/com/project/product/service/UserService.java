package com.project.product.service;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.product.dao.UserRepository;
import com.project.product.dto.UserDto;
import com.project.product.entities.User;
import com.project.product.exception.UserAlreadyExistException;

@Service
@Transactional
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDto registerUser(UserDto userDto) throws UserAlreadyExistException {
		final String email = userDto.getEmail();
		if (userRepository.existsByEmail(email)) {
			throw new UserAlreadyExistException(email);
		}
		logger.info("Register user with email: {}", email);
		User user = new User();
		String rawPassword = userDto.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setCreationDate(LocalDateTime.now());
		
		return new UserDto(userRepository.save(user));
	}

	public UserDto updateUser(UserDto user) {
		User userToUpdate = userRepository.findByEmail(user.getEmail());

		String rawPassword = user.getPassword();
		userToUpdate.setPassword(passwordEncoder.encode(rawPassword));
		userToUpdate.setUsername(user.getUsername());
		return new UserDto(userRepository.save(userToUpdate));
	}
	
	public UserDto getUser(String email) {
		return new UserDto(userRepository.findByEmail(email));
	}
	
	private User findUserById(Long id) {
		return userRepository.getReferenceById(id) ;
	}
}
