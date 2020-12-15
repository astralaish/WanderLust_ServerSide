package com.infy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.UserEntity;
import com.infy.model.Users;

@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Users getUserByContactNumber(String contactNumber) throws Exception {

		Query query = entityManager.createQuery("select u from UserEntity u where u.contactNumber = :contactNumber");
		query.setParameter("contactNumber", contactNumber);
		Users user = null;
		List<UserEntity> userEntities = query.getResultList();
		if (!userEntities.isEmpty()) {
			UserEntity userEntity = userEntities.get(0);
			user = new Users();
			user.setUserId(userEntity.getUserId());
			user.setEmailId(userEntity.getEmailId());
			user.setContactNumber(userEntity.getContactNumber());
			user.setUserName(userEntity.getUserName());
			user.setPassword(userEntity.getPassword());
		}
		return user;
	}

}
