package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleMapper {

    List<TRole> selectAll();

    List<TRole> selectRolesByUserId(@Param("userId") Integer userId);

    List<TRole> selectAllByCondition(@Param("search") String search);

}