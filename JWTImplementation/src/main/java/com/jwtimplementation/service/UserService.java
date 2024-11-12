package com.jwtimplementation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtimplementation.dto.LoginDto;
import com.jwtimplementation.entity.User;
import com.jwtimplementation.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public User signUp(User userDtata) {
		userDtata.setPassword(passwordEncoder.encode(userDtata.getPassword()));
		
		return userRepository.save(userDtata);
	}
	
	public User loginUser(LoginDto loginDto) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		return userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow();
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

}
