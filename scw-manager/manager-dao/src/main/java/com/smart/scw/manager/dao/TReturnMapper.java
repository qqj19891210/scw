package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TReturn;
import java.util.List;

public interface TReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    TReturn selectByPrimaryKey(Integer id);

    List<TReturn> selectAll();

    int updateByPrimaryKey(TReturn record);
}