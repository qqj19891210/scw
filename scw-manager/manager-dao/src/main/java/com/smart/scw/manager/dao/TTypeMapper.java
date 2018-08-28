package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TType;
import java.util.List;

public interface TTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TType record);

    TType selectByPrimaryKey(Integer id);

    List<TType> selectAll();

    int updateByPrimaryKey(TType record);
}