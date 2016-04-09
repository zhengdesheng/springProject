package com.exam.mserver.controller;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.mserver.common.persistence.util.UserUtil;
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
//		if(StringUtils.isNotBlank(username)){
//			System.out.println("进入判断");
//			User user = new User();
//			user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//			user.setAge(23);
//			user.setUserName(username);
//			try{
//				userMyBatis.save(user);
//			}catch(Exception e){
//				e.printStackTrace();
//				
//				 return "/sys/fail";
//			}
//			 return "/sys/success";
//		}
//		else{
//			//List<User> list = thisService.findAll();
//			User user = thisService.get("050b5c1ff55d468d8f135081631e2b7d");
//			System.out.println("user："+user.getUserName() +" user age:"+user.getAge());
//			
//			User user1 = thisService.get("050b5c1ff55d468d8f135081631e2b7d");
//			System.out.println("user1："+user1.getUserName() +" user1 age:"+user1.getAge());
////			if(null != list && list.size() >0){
////				User user1 = list.get(0);
////				System.out.println("用户的姓名："+user1.getUserName());
////			}
//			return "/sys/login";
//		}
		System.out.println("post请求");
		User user = UserUtil.getUser();
		
		if(null == user){
			return "/sys/fail";
		}else{
			 return "/sys/success";
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
    
	  /**
     * 管理登录
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("进入login success");
        return "/sys/success";
    }
    
    /**
     * 没有权限
     * 
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth")
    public String unauth(Model model) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        
        return "/unauth";
    }
    
    @RequestMapping(value = "/logout")
    public String logout(Model model) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated() == true) {
            SecurityUtils.getSubject().logout();
        }
        
      return "/sys/login";
    }
    
    public static void main(String[] args) {
    	String passWord = UserService.entryptPassword("123");
    	System.out.println(passWord);
	}
}
