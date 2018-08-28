package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TUserRole;
import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    List<TUserRole> selectAll();

    int updateByPrimaryKey(TUserRole record);
}