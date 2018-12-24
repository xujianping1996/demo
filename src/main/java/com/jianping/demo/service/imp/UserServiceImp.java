package com.jianping.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jianping.demo.dao.UserMapper;
import com.jianping.demo.entity.User;
import com.jianping.demo.service.UserService;
@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public User selectUserByLoginName(String username) {
		// TODO Auto-generated method stub
		
		return userMapper.selectUserByLoginName(username);
	}

}
