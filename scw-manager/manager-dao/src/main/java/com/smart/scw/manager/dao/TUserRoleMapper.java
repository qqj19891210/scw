package com.smart.scw.manager.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserRoleMapper {

    int insertRoleByIds(@Param("ids") List<Integer> ids,@Param("userId") Integer userId);

    int deleteRoleByIds(@Param("ids") List<Integer> ids,@Param("userId") Integer userId);

}