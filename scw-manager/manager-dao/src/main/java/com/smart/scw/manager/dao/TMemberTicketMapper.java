package com.smart.scw.manager.dao;

import com.smart.scw.manager.bean.TMemberTicket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMemberTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberTicket record);

    TMemberTicket selectByPrimaryKey(Integer id);

    List<TMemberTicket> selectAll();

    int updateByPrimaryKey(TMemberTicket record);

    List<TMemberTicket> selectByMemberId(@Param("memberid") Integer memberid);

}