package com.smart.scw.restapi.service.impl;

import com.smart.scw.manager.bean.TMemberTicket;
import com.smart.scw.manager.dao.TMemberTicketMapper;
import com.smart.scw.restapi.service.MemberTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberTicketServiceImpl implements MemberTicketService {

    @Autowired
    private TMemberTicketMapper memberTicketMapper;

    @Override
    public TMemberTicket getAuthTicket(Integer memberid) {
       List<TMemberTicket>  memberTickets = memberTicketMapper.selectByMemberId(memberid);
       int size=memberTickets.size();
       TMemberTicket memberTicket=memberTickets.get(size-1);
        return memberTicket;
    }

}
