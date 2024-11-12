package com.jwtimplementation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jwtimplementation.dto.LoginDto;
import com.jwtimplementation.entity.User;
import com.jwtimplementation.response.LoginResponse;
import com.jwtimplementation.service.JWTService;
import com.jwtimplementation.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTService jwtService;

	@PostMapping("/auth/signup")
	public ResponseEntity<User> postMethodName(@RequestBody User user){
		
		User user2 = userService.signUp(user);
		
		return ResponseEntity.ok(user2);
	} 
	
	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto){
		
		User user = userService.loginUser(loginDto);
		String jwtToken = jwtService.generateToken(new HashMap<>(), user);
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setTokenExpirTime(jwtService.getExpirationTime());
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users = userService.getAllUsers();
		
		return ResponseEntity.ok(users);
	}
}
