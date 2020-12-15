package com.infy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Users;
import com.infy.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("UserAPI")
public class UserAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<Users> authenticateUser(@RequestBody Users user) {

		try {
			Users userFromDB = userService.authenticateUser(user.getContactNumber(), user.getPassword());
			return new ResponseEntity<Users>(userFromDB, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}

}
