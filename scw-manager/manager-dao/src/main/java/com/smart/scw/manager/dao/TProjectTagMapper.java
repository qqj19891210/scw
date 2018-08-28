package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TProjectTag;
import java.util.List;

public interface TProjectTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTag record);

    TProjectTag selectByPrimaryKey(Integer id);

    List<TProjectTag> selectAll();

    int updateByPrimaryKey(TProjectTag record);
}