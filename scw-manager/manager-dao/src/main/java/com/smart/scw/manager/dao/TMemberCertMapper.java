package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMemberCert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberCert record);

    TMemberCert selectByPrimaryKey(Integer id);

    List<TMemberCert> selectAll();

    int updateByPrimaryKey(TMemberCert record);

    int insertMemberCert(@Param("memberCerts") List<TMemberCert> memberCerts);

    int deleteMemberCertByMemberId(Integer memberid);

    List<TMemberCert> getCertsByTicket(@Param("proId") String processInstanceId);

}