package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TAccountTypeCert;
import java.util.List;

public interface TAccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTypeCert record);

    TAccountTypeCert selectByPrimaryKey(Integer id);

    List<TAccountTypeCert> selectAll();

    int updateByPrimaryKey(TAccountTypeCert record);
}