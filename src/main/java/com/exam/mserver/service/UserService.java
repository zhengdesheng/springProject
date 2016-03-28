package com.exam.mserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.mserver.common.persistence.Parameter;
import com.exam.mserver.entity.User;
import com.exam.mserver.repository.hibernate.UserDao;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao thisDao;

	
	public User get(String id){
		return thisDao.get(id);
	}
	@Transactional(readOnly = false)
	public void save(User user) {
		thisDao.save(user);
	}

	
	public List<User> findAll() {
		String sql = "Select u from User u";
		return thisDao.find(sql, null);
	}
	
	public User findUserByName(String name){
		Parameter parameter = new Parameter();
		parameter.put("name", name);
		String hql = "Select u from User u where u.id = :name";
		return thisDao.getByHql(hql, parameter);
	}

}
