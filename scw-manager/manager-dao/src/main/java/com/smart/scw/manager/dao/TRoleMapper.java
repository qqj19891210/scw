package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TRole;
import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);
}