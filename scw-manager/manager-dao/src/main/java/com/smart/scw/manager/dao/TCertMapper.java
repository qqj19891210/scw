package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TCert;
import com.smart.scw.manager.bean.TMemberCert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCertMapper {

    List<TCert> selectAll();

    List<TCert> selectCertByAccountTypeId(@Param("atId") Integer accountTypeId);

}