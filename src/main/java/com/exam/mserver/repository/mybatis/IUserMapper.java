package com.exam.mserver.repository.mybatis;

import java.util.List;

import com.exam.mserver.entity.User;
import com.exam.mserver.persistence.annotation.MyBatisRepostory;

@MyBatisRepostory
public interface IUserMapper {

	List<User> findAll();
	
	void save(User user);
}
