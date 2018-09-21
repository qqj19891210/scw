package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TMember;
import com.smart.scw.manager.dao.TMemberMapper;
import com.smart.scw.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private TMemberMapper memberMapper;

    @Override
    public TMember getMemberByTicket(String processInstanceId) {
        return memberMapper.getMemberByTicket(processInstanceId);
    }

}
