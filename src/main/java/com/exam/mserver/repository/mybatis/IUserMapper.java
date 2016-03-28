package com.exam.mserver.repository.mybatis;

import java.util.List;

import com.exam.mserver.common.persistence.annotation.MyBatisRepostory;
import com.exam.mserver.entity.User;

@MyBatisRepostory
public interface IUserMapper {

	List<User> findAll();
	
	void save(User user);
}
