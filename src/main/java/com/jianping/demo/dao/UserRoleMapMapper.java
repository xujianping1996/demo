package com.jianping.demo.dao;

import com.jianping.demo.entity.UserRoleMap;

public interface UserRoleMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleMap record);

    int insertSelective(UserRoleMap record);

    UserRoleMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleMap record);

    int updateByPrimaryKey(UserRoleMap record);
}