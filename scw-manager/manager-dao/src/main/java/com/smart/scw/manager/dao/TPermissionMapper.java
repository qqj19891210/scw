package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TPermissionMapper {

    List<TPermission> selectAllMenus();

    List<TPermission> getRolePermission(@Param("rid") Integer rid);

}