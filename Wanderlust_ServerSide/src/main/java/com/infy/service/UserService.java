package com.infy.service;

import com.infy.model.Users;

public interface UserService {

	public Users authenticateUser(String contactNumber, String password) throws Exception;
	
}
