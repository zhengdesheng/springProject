package com.exam.mserver.common.persistence.util;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.hibernate.annotations.Cache;

import com.exam.mserver.common.persistence.util.MyRealm.Principal;
import com.exam.mserver.entity.User;
import com.exam.mserver.repository.hibernate.UserDao;
import com.google.common.collect.Maps;

public class UserUtil {

	private static UserDao userDao = ApplicationContextHelper
			.getBean(UserDao.class);
	public static final String CACHE_USER = "user";// 当前登录用户

	public static User getUser() {
		User user = (User) UserUtil.getCache(CACHE_USER);
		if (user == null) {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			System.out.println("principal:" + principal);
			if(null != principal){
				user = userDao.get(principal.getId());
				UserUtil.putCache(CACHE_USER, user);
			}

		}
		return user;
	}
 
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	public static Map<String, Object> getCacheMap() {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			return principal != null ? principal.getCacheMap() : map;
		} catch (UnavailableSecurityManagerException e) {
			e.printStackTrace();
		} catch (InvalidSessionException e) {
			e.printStackTrace();
		}
		return map;
	}
}
