package com.exam.mserver.repository.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

 

public class BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void save(T entity){
		try {
		Object id = null;
		for (Method method:entity.getClass().getMethods()) {
			Id idAnns = method.getAnnotation(Id.class);
			if(idAnns != null){
			
					id = method.invoke(entity);
				
				break;
			}
		}
        // 插入前执行方法
        if (StringUtils.isBlank((String) id)) {
        	System.out.println("methods的个数:"+entity.getClass().getMethods().length);
            for (Method method : entity.getClass().getMethods()) {
                PrePersist pp = method.getAnnotation(PrePersist.class);
                if (pp != null) {
                    method.invoke(entity);
                    break;
                }
            }
        }
        // 更新前执行方法
        else {
            for (Method method : entity.getClass().getMethods()) {
                PreUpdate pu = method.getAnnotation(PreUpdate.class);
                if (pu != null) {
                    method.invoke(entity);
                    break;
                }
            }
        }
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getSession().save(entity);
	}
}
