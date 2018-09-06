package com.smart.scw.manager.service;

public interface TUserRoleService {

    //给用户添加角色
    public void addRoles(String ids,Integer userId);

    //移除用户的某些角色
    public void deleteRoles(String ids,Integer userId);

}
