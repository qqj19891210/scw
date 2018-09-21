package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    TMember selectByPrimaryKey(Integer id);

    List<TMember> selectAll();

    int updateByPrimaryKey(TMember record);

    int selectByEmail(String email);

    int selectByLoginacct(String loginacct);

    TMember selectTMemberByLoginacct(String loginacct);

    int updateEmailByMemberId(@Param("email") String email,@Param("id") Integer id);

    TMember getMemberByTicket(@Param("proId") String processInstanceId);

}