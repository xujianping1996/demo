 package com.jianping.demo.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianping.demo.entity.User;
import com.jianping.demo.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@RequestMapping(value="/handler")
	public ModelAndView handler(ModelAndView mav) {
		mav.setViewName("login");
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
