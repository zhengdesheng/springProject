package com.exam.mserver.controller;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.mserver.entity.User;
import com.exam.mserver.repository.mybatis.IUserMapper;
import com.exam.mserver.service.UserService;

 

@Controller
public class LoginController {
	
	@Autowired
	private UserService thisService;
	@Autowired
	private IUserMapper userMyBatis;

	@RequestMapping(value="/login",method =RequestMethod.POST)
	public String login(String username,Model model,HttpServletRequest request,
			HttpServletResponse response){
		if(StringUtils.isNotBlank(username)){
			System.out.println("进入判断");
			User user = new User();
			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			user.setAge(23);
			user.setUserName(username);
			try{
				userMyBatis.save(user);
			}catch(Exception e){
				e.printStackTrace();
				
				 return "/sys/fail";
			}
			 return "/sys/success";
		}
		else{
			List<User> list = userMyBatis.findAll();
			System.out.println("list个个数是："+list.size());
			if(null != list && list.size() >0){
				User user1 = list.get(0);
				System.out.println("用户的姓名："+user1.getUserName());
			}
			return "/sys/login";
		}
			
	}
	
	  /**
     * 管理登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("进入login get");
        return "/sys/login";
    }
}
