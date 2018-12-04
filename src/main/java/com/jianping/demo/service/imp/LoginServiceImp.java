package com.jianping.demo.service.imp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jianping.demo.dao.UserMapper;
import com.jianping.demo.entity.User;
import com.jianping.demo.service.LoginService;
@Service
public class LoginServiceImp implements LoginService {
@Resource
private UserMapper userMapper;
	@Override
	public User verification(Integer username ,String password) {
		// TODO Auto-generated method stub
		System.out.println(username+"----"+password);
		HttpServletRequest httpServletRequest;
		return userMapper.selectByPrimaryKey(username);
	}
	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userMapper.insert(record);
	}

}
