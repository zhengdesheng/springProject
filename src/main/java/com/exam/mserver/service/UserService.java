package com.exam.mserver.service;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.mserver.entity.User;
import com.exam.mserver.repository.hibernate.UserDao;

@Service
@Transactional(readOnly = false)
public class UserService {

	@Autowired
	private UserDao thisDao;
	
	public void save(User user){
		thisDao.save(user);
	}
	
}
