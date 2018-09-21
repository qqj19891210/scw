package com.smart.scw.restapi.service;

import com.smart.scw.manager.bean.TMemberTicket;

public interface MemberTicketService {

    TMemberTicket getAuthTicket(Integer memberid);

}
