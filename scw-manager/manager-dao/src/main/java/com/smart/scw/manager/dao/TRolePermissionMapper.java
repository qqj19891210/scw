package com.smart.scw.manager.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRolePermissionMapper {

    int deletePermissionByRoleId(@Param("rid") Integer rid);

    int insertRoleAndPermission(@Param("pids") List<Integer> pids,@Param("rid") Integer rid);

}