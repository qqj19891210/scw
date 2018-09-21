package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TMember;

public interface MemberService {

    TMember getMemberByTicket(String processInstanceId);

}
