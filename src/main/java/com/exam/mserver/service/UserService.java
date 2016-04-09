package com.exam.mserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.mserver.common.persistence.Parameter;
import com.exam.mserver.common.persistence.util.Digests;
import com.exam.mserver.common.persistence.util.Encodes;
import com.exam.mserver.entity.User;
import com.exam.mserver.repository.hibernate.UserDao;
 

@Service
@Transactional(readOnly = true)
public class UserService {

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
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
		String hql = "Select u from User u where u.userName = :name";
		return thisDao.getByHql(hql, parameter);
	}
	
	   /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * 
     * @param plainPassword
     *            明文密码
     * @param password
     *            密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

}
