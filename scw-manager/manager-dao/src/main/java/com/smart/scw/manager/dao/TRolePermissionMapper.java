package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TRolePermission;
import java.util.List;

public interface TRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRolePermission record);

    TRolePermission selectByPrimaryKey(Integer id);

    List<TRolePermission> selectAll();

    int updateByPrimaryKey(TRolePermission record);
}