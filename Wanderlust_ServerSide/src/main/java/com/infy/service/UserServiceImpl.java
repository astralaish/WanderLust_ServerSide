package com.infy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dao.UserDAO;
import com.infy.model.Users;
import com.infy.utility.HashingUtility;
import com.infy.validator.UserValidator;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Users authenticateUser(String contactNumber, String password) throws Exception {

		UserValidator.validateUserForLogin(contactNumber, password);
		Users userFromDB = userDAO.getUserByContactNumber(contactNumber);
		if (userFromDB == null)
			throw new Exception("UserService.INVALID_CREDENTIALS");
		String passwordFromDB = userFromDB.getPassword();

		if (passwordFromDB != null) {
			String hashedPassword = HashingUtility.getHashValue(password);

			if (hashedPassword.equals(passwordFromDB)) {
				userFromDB.setPassword(null);
				return userFromDB;
			} else
				throw new Exception("UserService.INVALID_CREDENTIALS");
		} else
			throw new Exception("UserService.INVALID_CREDENTIALS");

	}

}
