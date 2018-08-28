package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TProject;
import java.util.List;

public interface TProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    TProject selectByPrimaryKey(Integer id);

    List<TProject> selectAll();

    int updateByPrimaryKey(TProject record);
}