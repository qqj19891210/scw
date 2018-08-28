package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TTag;
import java.util.List;

public interface TTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTag record);

    TTag selectByPrimaryKey(Integer id);

    List<TTag> selectAll();

    int updateByPrimaryKey(TTag record);
}