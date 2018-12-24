 package com.jianping.demo.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
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
	public ModelAndView handler(ModelAndView mav,HttpServletRequest request,String macAddr) {
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
		System.out.println(macAddr);
//		System.out.println("客户端IP:"+ip);
		return mav;
	}
	@RequestMapping(value = "/verification")
	public ModelAndView verification(ModelAndView mav,String username ,String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println(username+"----"+password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
//			<!-- 身份认证异常 -->  
//			<!-- 身份令牌异常，不支持的身份令牌 -->  
//			org.apache.shiro.authc.pam.UnsupportedTokenException  
//			<!-- 未知账户/没找到帐号,登录失败 -->  
//			org.apache.shiro.authc.UnknownAccountException  
//			<!-- 帐号锁定 -->  
//			org.apache.shiro.authc.LockedAccountException  
//			<!-- 用户禁用 -->  
//			org.apache.shiro.authc.DisabledAccountException  
//			<!-- 登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试 -->  
//			org.apache.shiro.authc.ExcessiveAttemptsException  
//			<!-- 一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录-->  
//			org.apache.shiro.authc.ConcurrentAccessException  
//			<!-- 账户异常 -->  
//			org.apache.shiro.authc.AccountException  
//			<!-- 过期的凭据异常 -->  
//			org.apache.shiro.authc.ExpiredCredentialsException  
//			<!-- 错误的凭据异常 -->  
//			org.apache.shiro.authc.IncorrectCredentialsException  
//			<!-- 凭据异常 -->  
//			org.apache.shiro.authc.CredentialsException  
//			org.apache.shiro.authc.AuthenticationException  
//			<!-- 权限异常 -->  
//			<!-- 没有访问权限，访问异常 -->  
//			org.apache.shiro.authz.HostUnauthorizedException  
//			org.apache.shiro.authz.UnauthorizedException  
//			<!-- 授权异常 -->  
//			org.apache.shiro.authz.UnauthenticatedException  
//			org.apache.shiro.authz.AuthorizationException  
//			<!-- shiro全局异常 -->  
//			org.apache.shiro.ShiroException
		}catch (UnsupportedTokenException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "身份令牌异常，不支持的身份令牌！");
			return mav;
		} catch (UnknownAccountException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "账户不存在！");
			return mav;
		} catch (LockedAccountException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "该账户已被锁定！");
			return mav;
		} catch (DisabledAccountException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "用户已被禁用！");
			return mav;
		} catch (ExcessiveAttemptsException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "重试操作频繁，请稍后重试！");
			return mav;
		} catch (ConcurrentAccessException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "该用户已再异地登陆！");
			return mav;
		} catch (AccountException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "账户异常！");
			return mav;
		} catch (ExpiredCredentialsException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "过期凭证异常！");
			return mav;
		} catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "错误凭证异常！");
			return mav;
		} catch (CredentialsException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "凭证异常！");
			return mav;
		} catch (AuthenticationException e) {
			// TODO: handle exception
			mav.setViewName("error");
			mav.addObject("masg", "凭证异常！");
			return mav;
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
