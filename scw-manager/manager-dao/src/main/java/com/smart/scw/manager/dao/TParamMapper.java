package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TParam;
import java.util.List;

public interface TParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TParam record);

    TParam selectByPrimaryKey(Integer id);

    List<TParam> selectAll();

    int updateByPrimaryKey(TParam record);
}