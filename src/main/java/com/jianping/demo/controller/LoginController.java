 package com.jianping.demo.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianping.demo.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value="/handler")
	public ModelAndView handler(ModelAndView mav,HttpServletRequest request) {
		mav.setViewName("login");
		String ip = request.getHeader("x-forwarded-for"); 
		if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 ||"unknow".equalsIgnoreCase(ip)) { 
		ip = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) { 
		ip = request.getRemoteAddr(); 
		} 
		logger.info("客户端IP:"+ip);
		return mav;
	}
	@RequestMapping(value = "/verification")
	public ModelAndView verification(ModelAndView mav,String username ,String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println(username+"----"+password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			mav.setViewName("error");
			mav.addObject("masg", e.getMessage());
			return mav;
		}
		mav.setViewName("/desktop/index");
		return mav;
	}
}
