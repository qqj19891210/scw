package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TOrder;
import java.util.List;

public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);
}