package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TProjectType;
import java.util.List;

public interface TProjectTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectType record);

    TProjectType selectByPrimaryKey(Integer id);

    List<TProjectType> selectAll();

    int updateByPrimaryKey(TProjectType record);
}