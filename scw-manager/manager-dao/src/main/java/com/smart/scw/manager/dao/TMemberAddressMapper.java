package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMemberAddress;
import java.util.List;

public interface TMemberAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberAddress record);

    TMemberAddress selectByPrimaryKey(Integer id);

    List<TMemberAddress> selectAll();

    int updateByPrimaryKey(TMemberAddress record);
}