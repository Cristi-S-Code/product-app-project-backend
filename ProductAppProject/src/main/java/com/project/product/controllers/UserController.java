package com.project.product.controllers;


import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.product.dto.UserDto;
import com.project.product.entities.User;
import com.project.product.exception.UserAlreadyExistException;
import com.project.product.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto registerUser(@Valid @RequestBody UserDto user) throws UserAlreadyExistException {
		logger.info("[REST] POST to /user/register");
		return userService.registerUser(user);
	};
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUser(@Valid @RequestBody UserDto user) {
		return userService.updateUser(user); 
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUser(Principal user) {
		return userService.getUser(user.getName()); 
	}
}
