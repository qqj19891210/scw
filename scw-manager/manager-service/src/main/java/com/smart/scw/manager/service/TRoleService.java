package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TRole;

import java.util.List;

public interface TRoleService {

    //获取所有权限
    List<TRole> getAllRoles();

    //根据用户id查询角色
    List<TRole> getUserRoles(Integer userId);

    //取两个集合的差集,得到用户未分配的权限
    List<TRole> unRoles(List<TRole> roles,List<TRole> copy,List<TRole> userRoles);

    List<TRole> getAllByCondition(String search);

}
