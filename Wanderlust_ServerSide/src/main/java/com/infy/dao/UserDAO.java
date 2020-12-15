package com.infy.dao;

import com.infy.model.Users;

public interface UserDAO {
	public Users getUserByContactNumber(String contactNumber) throws Exception;
}
