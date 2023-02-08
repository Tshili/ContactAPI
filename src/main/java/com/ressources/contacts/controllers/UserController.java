/**
 * 
 */
package com.ressources.contacts.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ressources.contacts.dto.LoginDto;
import com.ressources.contacts.dto.UserDto;
import com.ressources.contacts.entities.User;
import com.ressources.contacts.services.UserService;
import com.ressources.contacts.utils.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.HashMap;


/**
 * @author Bernard MPOY
 * 
 * Controller for users management
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private UserService userService;
	
	/**
	 * @param userService
	 */
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	// Create a usuer
	@Operation(summary = "Create a user")
	@PostMapping("/register")
	public  ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody UserDto userDto) {			
        User user = userService.registerUser(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword());
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
			
	}
	
	// log a user
	@Operation(summary = "User login")
	   @PostMapping("/login")
	    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginDto loginDto) {
	        String email = loginDto.getEmail();
	        String password = loginDto.getPassword();
	        User user = userService.validateUser(email, password);
	        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
	    }
	   
	   // Generate a token
	   private Map<String, String> generateJWTToken(User user) {
	        long timestamp = System.currentTimeMillis();
	        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
	                .setIssuedAt(new Date(timestamp))
	                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
	                .claim("userId", user.getUserId())
	                .claim("email", user.getEmail())
	                .claim("firstName", user.getFirstName())
	                .claim("lastName", user.getLastName())
	                .compact();
	        Map<String, String> map = new HashMap<>();
	        map.put("token", token);
	        return map;
	    }

}
