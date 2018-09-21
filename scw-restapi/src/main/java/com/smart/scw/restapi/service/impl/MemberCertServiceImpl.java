package com.smart.scw.restapi.service.impl;

import com.smart.scw.manager.bean.TMemberCert;
import com.smart.scw.manager.dao.TMemberCertMapper;
import com.smart.scw.restapi.service.MemberCertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberCertServiceImpl implements MemberCertService {

    @Autowired
    private TMemberCertMapper memberCertMapper;

    @Override
    public void addMemberCert(List<TMemberCert> memberCerts) {
        //先删除
        memberCertMapper.deleteMemberCertByMemberId(memberCerts.get(0).getMemberid());
        //后保存
        memberCertMapper.insertMemberCert(memberCerts);
    }

}
