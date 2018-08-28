package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMemberCert;
import java.util.List;

public interface TMemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberCert record);

    TMemberCert selectByPrimaryKey(Integer id);

    List<TMemberCert> selectAll();

    int updateByPrimaryKey(TMemberCert record);
}