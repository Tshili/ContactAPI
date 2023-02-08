/**
 * 
 */
package com.ressources.contacts.services;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ressources.contacts.entities.User;
import com.ressources.contacts.repositories.UserRepository;

/**
 * @author Bernard MPOY
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User validateUser(String email, String password) {
		if (email != null) {
			email = email.toLowerCase();
		}
		return userRepository.findByEmailAndPassword(email, password);
	}

	/*
	 * Method for creating a user 
	 * ToDo: I must encrypt the password, it is a bad practice to save an unencrypted password
	 */
	@Override
	public User registerUser(String firstName, String lastName, String email, String password) {

		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		User user = null;
		if (email != null) {
			email = email.toLowerCase();
		}

		if (!pattern.matcher(email).matches()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email format");
		}

		Boolean isUserExist = userRepository.findByEmail(email).isPresent();

		if (isUserExist) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email already in use");
		}

		if (StringUtils.isNotEmpty(password) && password.length() < 10) {

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"The password is too short (minimum 10 characters)");
		}

		user = new User(firstName, lastName, email, password);
		return userRepository.save(user);

	}

}
