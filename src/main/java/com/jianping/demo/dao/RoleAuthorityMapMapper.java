package com.jianping.demo.dao;

import com.jianping.demo.entity.RoleAuthorityMap;

public interface RoleAuthorityMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthorityMap record);

    int insertSelective(RoleAuthorityMap record);

    RoleAuthorityMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAuthorityMap record);

    int updateByPrimaryKey(RoleAuthorityMap record);
}