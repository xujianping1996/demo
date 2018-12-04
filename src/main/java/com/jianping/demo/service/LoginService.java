package com.jianping.demo.service;

import com.jianping.demo.entity.User;

public interface LoginService {
	public User verification(Integer username,String password);
	public int insert(User record);
}
