package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMember;
import java.util.List;

public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    TMember selectByPrimaryKey(Integer id);

    List<TMember> selectAll();

    int updateByPrimaryKey(TMember record);
}