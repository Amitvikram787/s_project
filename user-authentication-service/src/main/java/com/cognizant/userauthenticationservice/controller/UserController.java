package com.cognizant.userauthenticationservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.userauthenticationservice.entities.User;
import com.cognizant.userauthenticationservice.exception.UserAlreadyExistsException;
import com.cognizant.userauthenticationservice.service.AppUserDetailsService;

@RestController
public class UserController {
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable String id) {
		return appUserDetailsService.getUser(id);
	}
	
	@PostMapping("/users")
	public User signupUser(@RequestBody @Valid User user) throws UserAlreadyExistsException {
		User u = appUserDetailsService.signupUser(user);
		System.out.println(u.getUserId());
		return u;
	}
	
	@PostMapping("/managers")
	public User signupManager(@RequestBody User user) throws UserAlreadyExistsException {
		User u = appUserDetailsService.signupManager(user);
		System.out.println(u.getUserId());
		return u;
	}
	
	@PostMapping("/admins")
	public User signupAdmin(@RequestBody User user) throws UserAlreadyExistsException {
		User u = appUserDetailsService.signupAdmin(user);
		System.out.println(u.getUserId());
		return u;
	}
	
	
	@GetMapping("/users")
	public boolean userExists(@RequestParam("username") String username) {
		return this.appUserDetailsService.userExists(username);
	}
}